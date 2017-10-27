package com.adven.concordion.extensions.exam.rest.commands;

import com.adven.concordion.extensions.exam.PlaceholdersResolver;
import com.adven.concordion.extensions.exam.html.Html;
import com.adven.concordion.extensions.exam.html.RowParser;
import com.adven.concordion.extensions.exam.rest.JsonPrettyPrinter;
import com.jayway.restassured.response.Response;
import net.javacrumbs.jsonunit.core.Configuration;
import org.apache.commons.collections.map.HashedMap;
import org.concordion.api.CommandCall;
import org.concordion.api.CommandCallList;
import org.concordion.api.Evaluator;
import org.concordion.api.ResultRecorder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.adven.concordion.extensions.exam.html.Html.*;
import static com.adven.concordion.extensions.exam.rest.commands.RequestExecutor.fromEvaluator;
import static com.google.common.base.Strings.isNullOrEmpty;
import static net.javacrumbs.jsonunit.JsonAssert.assertJsonEquals;
import static org.apache.commons.lang3.StringUtils.isEmpty;

public class CaseCommand extends RestVerifyCommand {
    private static final String DESC = "desc";
    private static final String URL_PARAMS = "urlParams";
    private static final String COOKIES = "cookies";
    private static final String VARIABLES = "vars";
    private static final String VALUES = "vals";
    private static final String BODY = "body";
    private static final String EXPECTED = "expected";
    private static final String WHERE = "where";

    private final JsonPrettyPrinter jsonPrinter = new JsonPrettyPrinter();
    private List<Map<String, Object>> cases = new ArrayList<>();
    private int number = 0;
    private Configuration cfg;

    public CaseCommand(String tag, Configuration cfg) {
        super("case", tag);
        this.cfg = cfg;
    }

    @Override
    public void setUp(CommandCall commandCall, Evaluator eval, ResultRecorder resultRecorder) {
        Html caseRoot = new Html(commandCall.getElement());
        cases.clear();
        final Html where = caseRoot.first(WHERE);
        if (where != null) {
            String[] names = where.takeAwayAttr(VARIABLES, eval).split(",");
            for (List<Object> val : new RowParser(where, VALUES, eval).parse()) {
                Map<String, Object> caseParams = new HashedMap();
                for (int j = 0; j < names.length; j++) {
                    caseParams.put("#" + names[j].trim(), val.get(j));
                }
                cases.add(caseParams);
            }
        } else {
            cases.add(new HashedMap());
        }

        Html body = caseRoot.first(BODY);
        Html expected = caseRoot.first(EXPECTED);
        caseRoot.remove(body, expected);
        for (Map<String, Object> ignored : cases) {
            caseRoot.childs(
                    Html.tag("case").childs(
                            body == null ? null : Html.tag("body").text(body.text()),
                            Html.tag("expected").text(expected.text())
                    )
            );
        }
    }

    @Override
    public void execute(CommandCall cmd, Evaluator eval, ResultRecorder resultRecorder) {
        CommandCallList childCommands = cmd.getChildren();
        Html root = new Html(cmd.getElement());

        final RequestExecutor executor = fromEvaluator(eval);
        String urlParams = root.takeAwayAttr(URL_PARAMS);
        String cookies = root.takeAwayAttr(COOKIES);

        for (Map<String, Object> aCase : cases) {
            for (Map.Entry<String, Object> entry : aCase.entrySet()) {
                eval.setVariable(entry.getKey(), entry.getValue());
            }

            if (cookies != null) {
                executor.cookies(PlaceholdersResolver.resolveJson(cookies, eval));
            }

            executor.urlParams(urlParams == null ? null : PlaceholdersResolver.resolveJson(urlParams, eval));

            Html caseTR = tr().insteadOf(root.first("case"));
            Html body = caseTR.first("body");
            if (body != null) {
                Html td = td().insteadOf(body).css("json");
                String bodyStr = PlaceholdersResolver.resolveJson(td.text(), eval);
                td.removeAllChild().text(jsonPrinter.prettyPrint(bodyStr));
                executor.body(bodyStr);
            }

            final String expectedStatus = "HTTP/1.1 200 OK";
            Html statusTd = td(expectedStatus);
            caseTR.childs(statusTd);

            childCommands.setUp(eval, resultRecorder);
            Response response = executor.execute();
            eval.setVariable("#exam_response", response);
            childCommands.execute(eval, resultRecorder);
            childCommands.verify(eval, resultRecorder);

            vf(td().insteadOf(caseTR.first("expected")), eval, resultRecorder);

            String actualStatus = executor.statusLine();
            if (expectedStatus.equals(actualStatus)) {
                success(resultRecorder, statusTd.el());
            } else {
                failure(resultRecorder, statusTd.el(), actualStatus, expectedStatus);
            }
        }
    }

    @Override
    public void verify(CommandCall cmd, Evaluator evaluator, ResultRecorder resultRecorder) {
        RequestExecutor executor = fromEvaluator(evaluator);

        final String colspan = executor.hasRequestBody() ? "3" : "2";
        Html rt = new Html(cmd.getElement());
        String caseDesc = caseDesc(rt.attr(DESC), evaluator);
        rt.attr("data-type", "case").attr("id", caseDesc).above(
                tr().childs(
                        td(caseDesc).attr("colspan", colspan).muted()
                )
        );
    }

    private String caseDesc(String desc, Evaluator eval) {
        return ++number + ") " + (desc == null ? "" : PlaceholdersResolver.resolveJson(desc, eval));
    }

    private void vf(Html root, Evaluator eval, ResultRecorder resultRecorder) {
        final String expected = printer.prettyPrint(PlaceholdersResolver.resolveJson(root.text(), eval));
        root.removeAllChild().text(expected).css("json");

        RequestExecutor executor = fromEvaluator(eval);

        fillCaseContext(root, executor);

        String actual = executor.responseBody();
        if (isEmpty(actual)) {
            failure(resultRecorder, root, "(not set)", expected);
            return;
        }

        String prettyActual = printer.prettyPrint(actual);
        try {
            assertJsonEquals(expected, prettyActual, cfg);
            success(resultRecorder, root);
        } catch (AssertionError | Exception e) {
            e.printStackTrace();
            failure(resultRecorder, root, prettyActual, expected);
        }
    }

    private void fillCaseContext(Html root, RequestExecutor executor) {
        Html div = div().childs(
                italic(executor.requestMethod() + " "),
                code(executor.requestUrlWithParams())
        );

        String cookies = executor.cookies();
        if (!isNullOrEmpty(cookies)) {
            div.childs(
                    italic(" Cookies "),
                    code(cookies)
            );
        }

        root.parent().above(tr().childs(td().attr("colspan", executor.hasRequestBody() ? "3" : "2").childs(div)));
    }
}
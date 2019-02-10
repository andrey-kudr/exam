package com.adven.concordion.extensions.exam;

import com.adven.concordion.extensions.exam.commands.ExamCommand;
import com.adven.concordion.extensions.exam.configurators.DbTester;
import com.adven.concordion.extensions.exam.configurators.RestAssuredCfg;
import com.adven.concordion.extensions.exam.configurators.WebDriverCfg;
import com.adven.concordion.extensions.exam.files.DefaultFilesLoader;
import com.adven.concordion.extensions.exam.files.FilesLoader;
import com.adven.concordion.extensions.exam.mq.MqTester;
import com.adven.concordion.extensions.exam.rest.DateFormatMatcher;
import com.adven.concordion.extensions.exam.rest.DateWithin;
import com.adven.concordion.extensions.exam.rest.XMLDateWithin;
import net.javacrumbs.jsonunit.core.Configuration;
import org.concordion.api.extension.ConcordionExtender;
import org.concordion.api.extension.ConcordionExtension;
import org.dbunit.DataSourceDatabaseTester;
import org.dbunit.IDatabaseTester;
import org.hamcrest.Matcher;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.xmlunit.diff.DefaultNodeMatcher;
import org.xmlunit.diff.NodeMatcher;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

import static net.javacrumbs.jsonunit.JsonAssert.when;
import static net.javacrumbs.jsonunit.core.Option.IGNORING_ARRAY_ORDER;
import static org.xmlunit.diff.ElementSelectors.byName;
import static org.xmlunit.diff.ElementSelectors.byNameAndText;

public class ExamExtension implements ConcordionExtension {
    public static final String NS = "http://exam.extension.io";
    public static final DefaultNodeMatcher DEFAULT_NODE_MATCHER = new DefaultNodeMatcher(byNameAndText, byName);
    public static final Configuration DEFAULT_JSON_UNIT_CFG = when(IGNORING_ARRAY_ORDER)
            .withMatcher("formattedAs", new DateFormatMatcher())
            .withMatcher("formattedAndWithin", DateWithin.Companion.param())
            .withMatcher("formattedAndWithinNow", DateWithin.Companion.now())
            .withMatcher("xmlDateWithinNow", new XMLDateWithin());
    public static final FilesLoader DEFAULT_FILES_LOADER = new DefaultFilesLoader();
    private static DesiredCapabilities capabilities;
    private Configuration jsonUnitCfg;
    private IDatabaseTester dbTester;
    private Map<String, MqTester> mqTesters = new HashMap<>();
    private NodeMatcher nodeMatcher;
    private FilesLoader filesLoader;

    public ExamExtension() {
        jsonUnitCfg = DEFAULT_JSON_UNIT_CFG;
        nodeMatcher = DEFAULT_NODE_MATCHER;
        filesLoader = DEFAULT_FILES_LOADER;
    }

    private static DataSource lookUp(String jndi) {
        try {
            return (DataSource) new InitialContext().lookup(jndi);
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }

    private static void capabilities(DesiredCapabilities c) {
        capabilities = c;
    }

    @SuppressWarnings("unused")
    /**
     * matcherName - name to reference in placeholder
     * matcher - implementation
     * usage example:
     *              matcherName↓    ↓parameter
     * <datetime>!{xmlDateWithinNow 1min}</datetime>
     */
    public ExamExtension addPlaceholderMatcher(String matcherName, Matcher<?> matcher) {
        jsonUnitCfg = jsonUnitCfg.withMatcher(matcherName, matcher);
        return this;
    }

    public ExamExtension withXmlUnitNodeMatcher(NodeMatcher nodeMatcher) {
        this.nodeMatcher = nodeMatcher;
        return this;
    }

    public ExamExtension withFilesLoader(FilesLoader customFilesLoader) {
        this.filesLoader = customFilesLoader;
        return this;
    }

    @SuppressWarnings("unused")
    public WebDriverCfg ui() {
        return new WebDriverCfg(this);
    }

    @SuppressWarnings("unused")
    public ExamExtension webDriverCapabilities(DesiredCapabilities capabilities) {
        capabilities(capabilities);
        return this;
    }

    @SuppressWarnings("unused")
    public RestAssuredCfg rest() {
        return new RestAssuredCfg(this);
    }

    @SuppressWarnings("unused")
    public RestAssuredCfg rest(String url) {
        return new RestAssuredCfg(this, url);
    }

    @SuppressWarnings("unused")
    public DbTester db() {
        return new DbTester(this);
    }

    @SuppressWarnings("unused")
    public ExamExtension db(DataSource dataSource) {
        return dbTester(new DataSourceDatabaseTester(dataSource));
    }

    @SuppressWarnings("unused")
    public ExamExtension db(DataSource dataSource, String schema) {
        return dbTester(new DataSourceDatabaseTester(dataSource, schema));
    }

    @SuppressWarnings("unused")
    public ExamExtension db(String jndi, String schema) {
        return db(lookUp(jndi), schema);
    }

    @SuppressWarnings("unused")
    public ExamExtension db(String jndi) {
        return db(lookUp(jndi));
    }

    @SuppressWarnings("unused")
    public ExamExtension dbTester(IDatabaseTester dbTester) {
        this.dbTester = dbTester;
        return this;
    }

    public ExamExtension mq(Map<String, MqTester> mqTesters) {
        this.mqTesters.putAll(mqTesters);
        return this;
    }

    public ExamExtension mq(String name, MqTester mqTester) {
        mqTesters.put(name, mqTester);
        return this;
    }

    @Override
    public void addTo(ConcordionExtender ex) {
        new CodeMirrorExtension().addTo(ex);
        new BootstrapExtension().addTo(ex);

        final CommandRegistry registry = new CommandRegistry(
                dbTester, jsonUnitCfg, nodeMatcher, capabilities, filesLoader, mqTesters
        );

        for (ExamCommand cmd : registry.commands()) {
            if (!"example".equals(cmd.name())) {
                ex.withCommand(NS, cmd.name(), cmd);
            }
        }

        ex.withDocumentParsingListener(new ExamDocumentParsingListener(registry));
        ex.withSpecificationProcessingListener(new SpecSummaryListener());
        ex.withExampleListener(new ExamExampleListener());
    }
}
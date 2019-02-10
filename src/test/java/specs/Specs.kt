package specs

import com.adven.concordion.extensions.exam.ExamExtension
import com.adven.concordion.extensions.exam.mq.MqTesterAdapter
import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock.*
import com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig
import com.github.tomakehurst.wiremock.extension.responsetemplating.ResponseTemplateTransformer
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings
import org.concordion.api.AfterSuite
import org.concordion.api.BeforeSuite
import org.concordion.api.extension.Extension
import org.concordion.api.option.ConcordionOptions
import org.concordion.integration.junit4.ConcordionRunner
import org.concordion.internal.ConcordionBuilder.NAMESPACE_CONCORDION_2007
import org.junit.runner.RunWith
import java.util.*

@RunWith(ConcordionRunner::class)
@ConcordionOptions(declareNamespaces = ["c", NAMESPACE_CONCORDION_2007, "e", ExamExtension.NS])
open class Specs {

    @SuppressFBWarnings(value = ["URF_UNREAD_FIELD"], justification = "concordion extension declaration")
    @Extension
    private val exam = ExamExtension()
        .rest().port(PORT).end()
        .db().end()
        .ui().headless().end()
        .mq(mapOf("myQueue" to object : MqTesterAdapter() {
            private val queue = Stack<String>()

            override fun send(message: String) {
                queue.add(message)
            }

            override fun receive(): String = queue.pop()
        }))

    companion object {
        private const val PORT = 8888
        private var server: WireMockServer? = WireMockServer(
            wireMockConfig().extensions(ResponseTemplateTransformer(true)).port(PORT)
        )

        @JvmStatic
        @AfterSuite
        fun stopServer() {
            if (server != null) {
                server!!.stop()
                server = null
            }
        }

        @JvmStatic
        @BeforeSuite
        fun startServer() {
            server?.apply {
                val req = "{{{request.body}}}"
                val cookie = """{"cookies":"{{{request.cookies}}}"}"""
                val method = """{"{{{request.requestLine.method}}}":"{{{request.url}}}"}"""

                stubFor(
                    post(anyUrl()).atPriority(1).withHeader(
                        "Content-type", equalTo("application/soap+xml; charset=UTF-8;")
                    ).willReturn(
                        req status 200
                    )
                )

                stubFor(
                    get(urlPathEqualTo("/ui")).atPriority(1).willReturn(
                        "<html><head></head><body><span>Dummy page</span></body></html>" status 200
                    )
                )

                stubFor(
                    post(urlPathEqualTo("/status/400")).atPriority(1).willReturn(
                        req status 400
                    )
                )
                stubFor(
                    put(urlPathEqualTo("/status/400")).atPriority(1).willReturn(
                        req status 400
                    )
                )
                stubFor(
                    any(urlPathEqualTo("/status/400")).atPriority(2).willReturn(
                        method status 400
                    )
                )

                stubFor(
                    post(anyUrl()).withCookie("cook", matching(".*")).atPriority(3).willReturn(
                        cookie status 200
                    )
                )
                stubFor(
                    put(anyUrl()).withCookie("cook", matching(".*")).atPriority(3).willReturn(
                        cookie status 200
                    )
                )
                stubFor(
                    post(anyUrl()).atPriority(4).willReturn(
                        req status 200
                    )
                )
                stubFor(
                    put(anyUrl()).atPriority(4).willReturn(
                        req status 200
                    )
                )

                stubFor(
                    any(anyUrl()).withCookie("cook", matching(".*")).atPriority(5)
                        .willReturn(
                            aResponse()
                                .withHeader("my_header", "some value")
                                .withBody("""{"{{{request.requestLine.method}}}":"{{{request.url}}}", "cookies":"{{{request.cookies}}}"}""")
                        )
                )
                stubFor(any(anyUrl()).atPriority(6).willReturn(aResponse().withBody(method)))

                start()
            }
        }

        private infix fun String.status(status: Int) = aResponse().withBody(this).withStatus(status)
    }
}
package micronaut.fn.aws

import io.micronaut.context.ApplicationContext
import io.micronaut.runtime.server.EmbeddedServer
import spock.lang.Specification

class MicronautFnAwsFunctionSpec extends Specification {

    void "test micronaut-fn-aws function"() {
        given:
        EmbeddedServer server = ApplicationContext.run(EmbeddedServer)
        MicronautFnAwsClient client = server.getApplicationContext().getBean(MicronautFnAwsClient)

        expect:
        client.micronautFnAws().blockingGet() == "micronaut-fn-aws"

        cleanup:
        if(server != null) server.stop()
    }
}

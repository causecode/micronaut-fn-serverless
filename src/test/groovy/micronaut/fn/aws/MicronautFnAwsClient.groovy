package micronaut.fn.aws

import io.micronaut.function.client.FunctionClient
import io.reactivex.Single

import javax.inject.Named

@FunctionClient
interface MicronautFnAwsClient {

    @Named("micronaut-fn-aws")
    Single<String> micronautFnAws()
}

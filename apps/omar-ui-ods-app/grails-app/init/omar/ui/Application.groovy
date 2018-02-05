package omar.ui

import grails.boot.GrailsApp
import grails.boot.config.GrailsAutoConfiguration
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso
import org.springframework.security.oauth2.provider.OAuth2Authentication
import org.springframework.context.annotation.Bean
import org.springframework.web.context.request.RequestContextListener

import java.security.Principal

@EnableOAuth2Sso
class Application extends GrailsAutoConfiguration {
    static void main(String[] args) {
        GrailsApp.run(Application, args)
    }

    @Bean public RequestContextListener requestContextListener(){
        return new RequestContextListener();
    }

}

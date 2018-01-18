package omar.app

import omar.openlayers.OmarOpenlayersUtils
import omar.openlayers.OpenLayersConfig
import org.springframework.beans.factory.InitializingBean
import grails.converters.JSON

import omar.ui.OmarSitesConfig

import org.springframework.security.oauth2.provider.OAuth2Authentication
import org.springframework.security.core.context.SecurityContextHolder

import java.security.Principal

class OmarController /*implements InitializingBean*/
{
    def openlayers

  OpenLayersConfig openLayersConfig
  OmarSitesConfig omarSitesConfig

  def index()
  {
    OAuth2Authentication auth = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();

    // user information parameters coming in from application.yml
    def userInfo = grailsApplication.config.omar.app.userInfo

    // The value of the request header for the user's name
    def requestHeaderName = request.getHeader(userInfo.requestHeaderUserName)

    // We will use the requestHeaderUserNameDefault from the application.yml if the request
    // header name is null.  Otherwise we will use the value from the request.getHeader call.
    def userInfoName = (requestHeaderName == null) ?
      userInfo.requestHeaderUserNameDefault : requestHeaderName

    grailsApplication.config.omar.app.sites = omarSitesConfig.sites

    def clientConfig = [
      serverURL: getBaseUrl(),
      openlayers: openLayersConfig,
      params: grailsApplication.config.omar.app,
      userInfo: [name: userInfoName],
      userToken: auth.getDetails().getTokenValue(),
      user: auth.name
    ]

    [
        clientConfig: clientConfig
    ]

	}

  void afterPropertiesSet() throws Exception
  {
      openlayers = grailsApplication.config.omar.openlayers

      //  Collect baseMaps[x] named params as a list and use it to override baseMaps
      def newBaseMaps = openlayers.keySet()?.grep { it ==~ /baseMaps\[\d+\]/ }?.collect { openlayers[it] }

      if ( newBaseMaps )
      {
          openlayers.baseMaps = newBaseMaps
      }

  }
}

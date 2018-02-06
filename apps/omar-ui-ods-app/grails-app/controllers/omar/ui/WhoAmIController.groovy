package omar.ui

import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_USER'])
class WhoAmIController {

    def index() {
      render contentType: 'application/json', text: principal as JSON
    }
}

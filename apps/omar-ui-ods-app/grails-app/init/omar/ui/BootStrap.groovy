package omar.ui

class BootStrap {

    def init = { servletContext ->
      AuthUser user = AuthUser.findOrSaveWhere(username: 'user', password: 'user')
      Role userRole = Role.findOrSaveWhere(authority: 'ROLE_USER')

      AuthUserRole.create(user, userRole)      
    }
    def destroy = {
    }
}

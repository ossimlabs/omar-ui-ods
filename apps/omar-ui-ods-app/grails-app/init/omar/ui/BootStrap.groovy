package omar.ui

class BootStrap {

    def init = { servletContext ->
      AuthUser user = AuthUser.findOrSaveWhere(username: 'user', password: 'user')
      Role userRole = Role.findOrSaveWhere(authority: 'ROLE_USER')
      Role githubRole = Role.findOrSaveWhere(authority: 'ROLE_GITHUB')
      Role facebookRole = Role.findOrSaveWhere(authority: 'ROLE_FACEBOOK')

      AuthUserRole.create(user, userRole)
    }
    def destroy = {
    }
}

package omar.ui

import grails.gorm.DetachedCriteria
import groovy.transform.ToString

import org.codehaus.groovy.util.HashCodeHelper
import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
@ToString(cache=true, includeNames=true, includePackage=false)
class AuthUserRole implements Serializable {

	private static final long serialVersionUID = 1

	AuthUser authUser
	Role role

	@Override
	boolean equals(other) {
		if (other instanceof AuthUserRole) {
			other.authUserId == authUser?.id && other.roleId == role?.id
		}
	}

    @Override
	int hashCode() {
	    int hashCode = HashCodeHelper.initHash()
        if (authUser) {
            hashCode = HashCodeHelper.updateHash(hashCode, authUser.id)
		}
		if (role) {
		    hashCode = HashCodeHelper.updateHash(hashCode, role.id)
		}
		hashCode
	}

	static AuthUserRole get(long authUserId, long roleId) {
		criteriaFor(authUserId, roleId).get()
	}

	static boolean exists(long authUserId, long roleId) {
		criteriaFor(authUserId, roleId).count()
	}

	private static DetachedCriteria criteriaFor(long authUserId, long roleId) {
		AuthUserRole.where {
			authUser == AuthUser.load(authUserId) &&
			role == Role.load(roleId)
		}
	}

	static AuthUserRole create(AuthUser authUser, Role role, boolean flush = false) {
		def instance = new AuthUserRole(authUser: authUser, role: role)
		instance.save(flush: flush)
		instance
	}

	static boolean remove(AuthUser u, Role r) {
		if (u != null && r != null) {
			AuthUserRole.where { authUser == u && role == r }.deleteAll()
		}
	}

	static int removeAll(AuthUser u) {
		u == null ? 0 : AuthUserRole.where { authUser == u }.deleteAll() as int
	}

	static int removeAll(Role r) {
		r == null ? 0 : AuthUserRole.where { role == r }.deleteAll() as int
	}

	static constraints = {
	    authUser nullable: false
		role nullable: false, validator: { Role r, AuthUserRole ur ->
			if (ur.authUser?.id) {
				if (AuthUserRole.exists(ur.authUser.id, r.id)) {
				    return ['userRole.exists']
				}
			}
		}
	}

	static mapping = {
		id composite: ['authUser', 'role']
		version false
	}
}

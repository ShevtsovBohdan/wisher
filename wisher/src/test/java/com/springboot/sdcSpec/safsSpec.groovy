package com.springboot.sdcSpec

import spock.lang.Specification;

class safsSpec extends Specification{
    def "should throw exception if user's name is Michael otherwise no exception should be thrown"() {
        given:
        UserService service = Stub()
        service.save({ User user -> 'Michael' == user.name }) >> {
            throw new IllegalArgumentException("We don't want you here, Micheal!")
        }

        when:
        User user = new User(name: 'Michael')
        service.save(user)
        then:
        notThrown(IllegalArgumentException)

        when:
        User user2 = new User(name: 'Lucas')
        service.save(user2)
        then:
        notThrown(IllegalArgumentException)
    }
}

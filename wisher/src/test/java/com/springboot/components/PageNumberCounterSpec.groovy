import com.springboot.components.PageNumberCounterImpl
import com.springboot.domain.User
import com.springboot.interfaces.ManageUser
import com.springboot.interfaces.ManageWish
import com.springboot.interfaces.PageNumberCounter
import com.springboot.userdetails.UserHelper
import org.springframework.security.core.context.SecurityContext
import spock.lang.Shared
import spock.lang.Specification

class PageNumberCounterSpec extends Specification{
    @Shared
    PageNumberCounterImpl pageNumberCounter;

    def setup(){
        ManageUser manageUser = Mock()
        ManageWish manageWish = Mock()
        pageNumberCounter = new PageNumberCounterImpl();
        pageNumberCounter.manageWish = manageWish
        pageNumberCounter.manageUser = manageUser
    }

    def "testing Counter's method"(){
        expect:
        pageNumberCounter.countForAllUsersPage() == 1

    }

    def "testing Counter's method 1"(){

    }
}
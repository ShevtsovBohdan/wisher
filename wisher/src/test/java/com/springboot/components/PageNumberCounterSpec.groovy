import com.springboot.components.PageNumberCounterImpl
import com.springboot.domain.User
import com.springboot.userdetails.interfaces.GetUser
import com.springboot.persistence.interfaces.ManageWish
import spock.lang.Specification

class PageNumberCounterSpec extends Specification{

    PageNumberCounterImpl pageNumberCounter = new PageNumberCounterImpl()

    def "testing countForMainPage method with numberOfRows = 40"(){
        given:
        def manageWish = Stub(ManageWish){
            numberOfRows(_) >> 40
        }
        def getUser = Stub(GetUser){
            getActiveUser(_) >> new User(userID: 1)
        }
        pageNumberCounter.manageWish = manageWish
        pageNumberCounter.getUser = getUser
        expect:
        pageNumberCounter.countForMainPage() == 4

    }

    def "testing countForAllUsersPage method with numberOfRows = 40"(){
        given:
        def manageWish = Stub(ManageWish){
            numberOfRows() >> 40
        }
        pageNumberCounter.manageWish = manageWish
        expect:
        pageNumberCounter.countForAllUsersPage() == 3

    }

    def "testing countForAllUsersPage method"(){
        given:
        def manageWish = Stub(ManageWish){
            numberOfRows() >> 12
        }
        pageNumberCounter.manageWish = manageWish;
        expect:
        pageNumberCounter.countForAllUsersPage() == 1

    }
}
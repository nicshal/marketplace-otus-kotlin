import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class JUnitMessageTestCase {
    
    @Test
    fun `test message with Mr`() {
        val msg = replaceMessage("Mr Bob")

        Assertions.assertEquals("Ms Bob", msg)
    }

    @Test
    fun `test message without Mr`() {
        val msg = replaceMessage("wwwwww Bob")

        Assertions.assertEquals("wwwwww Bob", msg)
    }
   
}
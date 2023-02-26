import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.string.shouldContain

class KotestTestMessage : StringSpec({

    "message should contains Ms" {
        replaceMessage("Mr Bob") shouldContain "Ms"
    }

})
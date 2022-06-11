import com.fake.FakeClass

@FakeClass
fun bbshs() = Unit

fun main() {
    FakeBbshs()
    FakeBbshs.FakeInnerClassStatic()
    FakeBbss.fakeFieldStatic
    FakeBbss().fakeMethod()
}

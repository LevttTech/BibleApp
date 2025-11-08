package com.levtttech.bibleapp.core

import com.levtttech.bibleapp.core.Abstract.Mapper
import org.junit.Assert.assertTrue
import org.junit.Test
import java.io.IOException

class AbstractTest {


    @Test
    fun test_success() {
        val dataObject = TestDataObject.Success("A", "B")
        val domainObject = dataObject.map(DataMapper.Base())
        val uiObject = domainObject.map(DomainMapper.Base())
        assertTrue(domainObject is DomainObject.Success)
        assertTrue(uiObject is UiObject.Success)

    }

    @Test
    fun test_fail() {
        val dataObject = TestDataObject.Fail(IOException())
        val domainObject = dataObject.map(DataMapper.Base())
        val uiObject = domainObject.map(DomainMapper.Base())
        assertTrue(domainObject is DomainObject.Fail)
        assertTrue(uiObject is UiObject.Fail)
    }

    private sealed class TestDataObject : Abstract.Object<DomainObject, DataMapper>() {
        class Success(
            private val textOne: String,
            private val textTwo: String,
        ) : TestDataObject() {
            override fun map(mapper: DataMapper): DomainObject {
                return mapper.map(textOne, textTwo)
            }
        }

        class Fail(private val exception: Exception) : TestDataObject() {
            override fun map(mapper: DataMapper): DomainObject {
                return mapper.map(exception)
            }
        }
    }

    private interface DataMapper : Mapper {
        fun map(textOne: String, textTwo: String): DomainObject
        fun map(exception: Exception): DomainObject

        class Base : DataMapper {
            override fun map(
                textOne: String,
                textTwo: String,
            ): DomainObject {
                return DomainObject.Success("$textOne $textTwo")
            }

            override fun map(exception: Exception): DomainObject {
                return DomainObject.Fail(exception)
            }
        }
    }

    private sealed class DomainObject : Abstract.Object<UiObject, DomainMapper>() {
        class Success(private val textCombined: String) : DomainObject() {
            override fun map(mapper: DomainMapper): UiObject {
                return mapper.map(textCombined)
            }
        }

        class Fail(private val exception: Exception) : DomainObject() {
            override fun map(mapper: DomainMapper): UiObject {
                return mapper.map(exception)
            }
        }
    }

    private interface DomainMapper : Mapper {
        fun map(textCombined: String): UiObject

        fun map(exception: Exception): UiObject

        class Base : DomainMapper {
            override fun map(textCombined: String): UiObject {
                return UiObject.Success(textCombined.uppercase())
            }

            override fun map(exception: Exception): UiObject {
                return UiObject.Fail(exception.message.toString())
            }
        }
    }
    private sealed class UiObject : Abstract.Object<Unit, Mapper.Empty>() {
        class Success(private val textUi: String) : UiObject() {
            override fun map(mapper: Mapper.Empty) {
                TODO("Not yet implemented")
            }
        }

        class Fail(private val errorMessage: String) : UiObject() {
            override fun map(mapper: Mapper.Empty) {
                TODO("Not yet implemented")
            }
        }
    }

}
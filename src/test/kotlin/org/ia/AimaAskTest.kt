package org.ia

import org.ia.bnparser.BIFReader
import org.junit.Test
import kotlin.test.assertEquals
import aima.core.probability.bayes.exact.EnumerationAsk;

class AimaAskTest {

    @Test
    fun testInferenceByEnumeration() {
        val network = BIFReader.loadFromResource("networks/earthquake.xbif")


//        val result = EnumerationAsk().ask(
//            arrayOf()
//        )



    }

}

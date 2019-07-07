package org.ia

import org.ia.bnparser.BIFReader
import org.junit.Test

class BayesNetExtensionsTest {

    @Test
    fun testGetVariablesMap() {
        val network = BIFReader.loadFromResource("networks/earthquake.xbif")
        val alarm = network.variables["Alarm"]
        println(alarm)
    }
}
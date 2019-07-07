package org.ia.bnparser

import aima.core.probability.bayes.BayesianNetwork
import java.io.FileNotFoundException
import java.io.InputStream
import javax.xml.parsers.SAXParserFactory

public object BIFReader {

    private val saxParser = SAXParserFactory.newInstance().newSAXParser()

    public fun load(input: InputStream): BayesianNetwork {
        val bifHandler = MyBIFHandler()
        saxParser.parse(input, bifHandler)
        return bifHandler.network
    }

    public fun loadFromResource(path: String): BayesianNetwork {
        val resourceStream = this.javaClass.classLoader.getResourceAsStream(path)
        if (resourceStream != null) {
            return load(resourceStream)
        }

        throw FileNotFoundException("Resource in $path not found.")
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val bn = loadFromResource("networks/earthquake.xbif")

        bn.variablesInTopologicalOrder.forEach {
            println(it.name)
        }
    }
}

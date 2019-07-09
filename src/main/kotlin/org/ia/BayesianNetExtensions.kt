package org.ia

import aima.core.probability.RandomVariable
import aima.core.probability.bayes.BayesianNetwork
import java.util.*

val variablesMapCache = WeakHashMap<BayesianNetwork, Map<String, RandomVariable>>()

val BayesianNetwork.variables: Map<String, RandomVariable>
    get() {
        if (this in variablesMapCache) {
            return variablesMapCache[this]!!
        }
        val variablesMap = sortedMapOf(
            *this.variablesInTopologicalOrder.map {
                it.name to it
            }.toTypedArray()
        )
        variablesMapCache[this] = variablesMap
        return variablesMap
    }

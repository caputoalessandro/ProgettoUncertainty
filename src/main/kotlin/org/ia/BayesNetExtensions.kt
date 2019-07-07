package org.ia

import aima.core.probability.RandomVariable
import aima.core.probability.bayes.BayesianNetwork

val BayesianNetwork.variables: Map<String, RandomVariable>
    get() {
        val variablesMap by lazy {
            sortedMapOf(
                *this.variablesInTopologicalOrder.map {
                    it.name to it
                }.toTypedArray()
            )
        }
        return variablesMap
    }

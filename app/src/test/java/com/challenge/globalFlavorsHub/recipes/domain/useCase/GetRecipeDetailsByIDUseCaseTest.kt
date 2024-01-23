package com.challenge.globalFlavorsHub.recipes.domain.useCase

import com.challenge.globalFlavorsHub.domain.GetRecipeDetailsByIDUseCase
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Test

class GetRecipeDetailsByIDUseCaseTest {
    @Test
    fun invokeGetRecipeDetailByIDOneTest() {
        runBlocking {
            GetRecipeDetailsByIDUseCase().invoke(
                id = 1,
                dispatcher = Dispatchers.Unconfined
            ).collect {
                assertEquals()
            }
        }
    }
}

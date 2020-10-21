package com.gln.kotlindemo

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.*
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@ExtendWith(SpringExtension::class)
@WebMvcTest(SmartphoneController::class)
class SmartphoneControllerTest {

    @Autowired
    lateinit private var mockMvc: MockMvc

    @MockBean
    private lateinit var repository: SmartphoneRepository

    @BeforeEach
    fun setup() {
        val oppo = Smartphone(1, "OPPO F1", "OPPO", OsType.ANDROID, 120.00)
        val iphone = Smartphone(2, "Iphone 6", "Iphone", OsType.IOS, 140.00)
        val oppo2 = Smartphone(3, "OPPO F2", "OPPO", OsType.ANDROID, 130.00)
        val iphone2 = Smartphone(4, "Iphone 5", "Iphone", OsType.IOS, 150.00)
        given(this.repository.findAll()).willReturn(listOf(oppo, iphone, oppo2, iphone2))
        given(this.repository.findByBrand("OPPO")).willReturn(listOf(oppo, oppo2))
        given(this.repository.findByBrand("Iphone")).willReturn(listOf(iphone, iphone2))
    }

    @Test
    fun testShowSmarphonesList() {
        val actions = mockMvc.perform(MockMvcRequestBuilders.get("/smartphones").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk)
        actions.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value(1))
    }

    @Test
    fun testShowSmarphonesListByBrand() {
        val brand = "Iphone"
        val actions = mockMvc.perform(MockMvcRequestBuilders.get("/smartphones/{brand}", brand).accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk)
        actions.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value(2))

        Mockito.verify(repository, Mockito.times(1)).findByBrand(brand)
    }

}
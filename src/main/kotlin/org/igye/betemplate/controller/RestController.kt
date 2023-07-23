package org.igye.betemplate.controller

import org.igye.betemplate.dto.BeRespose
import org.igye.betemplate.dto.Resp
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class RestController {
    data class Method1Req(val msg: String)
    data class Method1Resp(val len: Int)
    @PostMapping("/method1")
    fun method1(@RequestBody body:Method1Req): BeRespose<Method1Resp> = BeRespose {
        Method1Resp(len = body.msg.length)
    }

    data class Method2Req(val msg: String)
    data class Method2Resp(val len: Int)
    @PostMapping("/method2")
    fun method2(@RequestBody body:Method2Req): Resp<Method2Resp> = Resp.get {
        Method2Resp(len = body.msg.length)
    }
}
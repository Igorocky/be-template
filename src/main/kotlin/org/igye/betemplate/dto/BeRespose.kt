/**
 * MIT License
 *
 * Copyright (c) 2023-present Igor Ieskov (https://github.com/Igorocky/be-template)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 * */

package org.igye.betemplate.dto

data class BeErr(val code: Long, val msg: String)

data class BeRespose<T>(val data: T? = null, val err: BeErr? = null) {
    fun <B> mapData(mapper:(T) -> B): BeRespose<B> = if (data != null) {
        BeRespose(data = mapper(data))
    } else {
        (this as BeRespose<B>)
    }

    companion object {
        operator fun <T> invoke(errCode: Long = -1, errHandler: ((Exception) -> T)? = null, body: () -> T): BeRespose<T> {
            return try {
                BeRespose(data = body())
            } catch (ex: Exception) {
                try {
                    BeRespose(data = if (errHandler != null) errHandler(ex) else throw ex)
                }  catch (ex2: Exception) {
                    BeRespose(
                        err = BeErr(
                            code = errCode,
                            msg = "(${ex2.javaClass.canonicalName}) ${ex2.message}"
                        )
                    )
                }
            }
        }
    }
}

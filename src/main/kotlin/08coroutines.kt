import kotlinx.coroutines.*
import kotlin.random.Random

fun main() = runBlocking {
    // Default start of coroutine
    val global = launchInGlobalScope()

    // Launch as job and wait for finalization
    val job = launch { doSomething() }
    job.join()
    println("job 1 finished")
    global.join()
    println("job 2 finished")

    // A parent waits for the underlying child coroutines
    invokeAsParent()

    // Cancellation of jobs:
    println("Running infinitely")
    val job2 = launch { runInfinite() }
    delay(5000)
    job2.cancelAndJoin()

    println("Job was canceled")

// This will produce a TimeoutCancellationException and needs a try catch
//    println("Calling job with timeout")
//    withTimeout(3000, { runInfinite() })
//    println("Timeout after 3 seconds")


    // will be called in the same scope and not executed asynchronously
    showAnswer(10)
    guessAnswer(10, 20)

    // launch returns a job and async returns a Deferred<T> which can await a result
    val show = async { showAnswer(10) }
    val guess = async { guessAnswer(10, 20) }

    println("Awaiting the results")
    awaitAll(show, guess);

    println("Awaiting value")
    val value = async { returnValueAfterTime(100) }
    println("The value is ${value.await()}")

    println("Goodbye")
}

// How to simply launch a coroutine
fun launchInGlobalScope() : Job{
    return  GlobalScope.launch {
        delay(2000)
        println("I started after two seconds")
    }
}

// Suspending functions can be called in the context of a coroutine
// You cannot call them outside of a coroutine.
suspend fun doSomething(){
    delay(1000)
    println("I do something after 1 second")
}

suspend fun showAnswer(i: Int) {
    delay(2000)
    println("The answer is $i")
}

suspend fun guessAnswer(i: Int, j: Int) {
    delay(2000)
    println("I guessed ${Random.nextInt(i, j)}")
}

suspend fun runInfinite(){
    while (true){
        delay(1000)
        println("Still running")
    }
}

fun invokeAsParent() = runBlocking {
    val job = launch {
        repeat(3) {i ->
            launch {
                delay(1000L)
                println("Coroutine $i has ended")
            }
        }
    }
    // This will wait until all subsequent coroutines are finalized
    job.join()
}

suspend fun returnValueAfterTime(value: Int): Int {
    delay(1000)
    return value
}



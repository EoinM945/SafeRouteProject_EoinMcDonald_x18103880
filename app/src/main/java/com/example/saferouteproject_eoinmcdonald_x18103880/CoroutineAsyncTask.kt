package com.example.saferouteproject_eoinmcdonald_x18103880


import kotlinx.coroutines.*

    /*
    This class is not used as such. I was trying to find an alternative to the
    depreciated AsyncTask method in my main maps activity and followed a tutorial
    on how to build a replacement and this is the result although it is not in use
    as I could not make it work and thus had to use the depreciated asynctask
    as I could not master the other alternative which is Kotlin Coroutines.

     */

abstract class CoroutineAsyncTask<Params, Progress, Result> {

    private val coroutineExceptionHandler = CoroutineExceptionHandler{ _, throwable ->
        throwable.printStackTrace()
    }

    open fun onPreExecute(){}

    abstract fun doInBackground(vararg params: Params?): Result

    open fun onProgressUpdate(vararg values: Progress?){}

    open fun onPostExecute(result: Result?){}

    open fun onCancelled(result: Result?){}

    protected fun publishProgress(vararg progress: Progress?){
        GlobalScope.launch(Dispatchers.Main){
            onProgressUpdate(*progress)
        }
    }

    fun execute(vararg params: Params?){

        GlobalScope.launch(Dispatchers.Main){
         val result = doInBackground(*params)

         withContext(Dispatchers.Main){
             onPostExecute(result)
         }
        }

    }

    /*fun cancel(mayInterruptedIfRunning: Boolean){

    }*/


}
/*
 * System.Reflection.Emit-like API for writing .NET assemblies in MSIL
 */


package ch.epfl.lamp.compiler.msil.emit

import java.io.FileWriter
import java.io.BufferedWriter
import java.io.PrintWriter
import java.io.IOException
import java.util.Iterator
import java.util.HashMap
import java.util.Arrays

import ch.epfl.lamp.compiler.msil._
import ch.epfl.lamp.compiler.msil.emit
import ch.epfl.lamp.compiler.msil.util.Table

/**
 * The MSIL printer Visitor. It prints a complete
 * assembly in a single file that can be compiled by ilasm.
 *
 * @author Nikolay Mihaylov
 * @author Daniel Lorch
 * @version 1.0
 */
final class SingleFileILPrinterVisitor(_fileName: String) extends ILPrinterVisitor {
    var fileName: String = _fileName

    out = new PrintWriter(new BufferedWriter(new FileWriter(fileName)))
<<<<<<< HEAD
    
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   /**
     * Visit an AssemblyBuilder
     */
   @throws(classOf[IOException])
   def caseAssemblyBuilder(assemblyBuilder: AssemblyBuilder) {
	ILPrinterVisitor.currAssembly = assemblyBuilder

	// first get the entryPoint
	this.entryPoint = assemblyBuilder.EntryPoint

	// all external assemblies
	as = assemblyBuilder.getExternAssemblies()
  scala.util.Sorting.quickSort(as)(assemblyNameComparator) // Arrays.sort(as, assemblyNameComparator)

        assemblyBuilder.generatedFiles += fileName
	printAssemblyBoilerplate()

	// print each module
        var m: Array[Module] = assemblyBuilder.GetModules()
        nomembers = true
<<<<<<< HEAD
        for(val i <- 0 until m.length) {
=======
        for(i <- 0 until m.length) {
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
	    print(m(i).asInstanceOf[ModuleBuilder])
	}

        nomembers = false
<<<<<<< HEAD
        for(val i <- 0 until m.length) {
=======
        for(i <- 0 until m.length) {
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
	    print(m(i).asInstanceOf[ModuleBuilder])
	}
	// close out file
	out.close()
	ILPrinterVisitor.currAssembly = null
    }

    /**
     * Visit a ModuleBuilder
     */
    @throws(classOf[IOException])
    def caseModuleBuilder(module: ModuleBuilder) {
	// print module declaration
	currentModule = module
        if (nomembers) {
            print(".module \'"); print(module.Name); println("\'")
            printAttributes(module)
        }

	if (!module.globalsCreated)
	    module.CreateGlobalFunctions()

	var m: Array[MethodInfo] = module.GetMethods()
<<<<<<< HEAD
        for(val i <- 0 until m.length) {
=======
        for(i <- 0 until m.length) {
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
	    print(m(i).asInstanceOf[MethodBuilder])
	}

	var t: Array[Type] = module.GetTypes()
<<<<<<< HEAD
        for(val i <- 0 until t.length) {
=======
        for(i <- 0 until t.length) {
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
	    print(t(i).asInstanceOf[TypeBuilder])
	}
	currentModule = null
    }

}  // class SingleFileILPrinterVisitor

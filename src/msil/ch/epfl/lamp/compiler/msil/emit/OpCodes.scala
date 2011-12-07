/*
 * System.Reflection.Emit-like API for writing .NET assemblies to MSIL
 */


package ch.epfl.lamp.compiler.msil.emit


/**
 * Provides field representations of the Microsoft Intermediate Language (MSIL)
 * instructions for emission by the ILGenerator class members (such as Emit).
 *
 * @author Nikolay Mihaylov
 * @version 1.0
 */
object OpCodes {

    //##########################################################################

    /**
     * Adds two values and pushes the result onto the evaluation stack.
     */
     final val Add = OpCode.Add

    /**
<<<<<<< HEAD
     * Fills space if bytecodes are patched. No meaningful operation is performed 
=======
     * Fills space if bytecodes are patched. No meaningful operation is performed
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     * although a processing cycle can be consumed.
     */
     final val Nop = OpCode.Nop

    /**
<<<<<<< HEAD
     * Signals the Common Language Infrastructure (CLI) to inform the debugger that 
=======
     * Signals the Common Language Infrastructure (CLI) to inform the debugger that
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     * a break point has been tripped.
     */
     final val Break = OpCode.Break

    /**
     * Loads the argument at index 0 onto the evaluation stack.
     */
     final val Ldarg_0 = OpCode.Ldarg_0

    /**
     * Loads the argument at index 1 onto the evaluation stack.
     */
     final val Ldarg_1 = OpCode.Ldarg_1

    /**
     * Loads the argument at index 2 onto the evaluation stack.
     */
     final val Ldarg_2 = OpCode.Ldarg_2

    /**
     * Loads the argument at index 3 onto the evaluation stack.
     */
     final val Ldarg_3 = OpCode.Ldarg_3

    /**
     * Loads the local variable at index 0 onto the evaluation stack.
     */
     final val Ldloc_0 = OpCode.Ldloc_0

    /**
     * Loads the local variable at index 1 onto the evaluation stack.
     */
     final val Ldloc_1 = OpCode.Ldloc_1

    /**
     * Loads the local variable at index 2 onto the evaluation stack.
     */
     final val Ldloc_2 = OpCode.Ldloc_2

    /**
     * Loads the local variable at index 3 onto the evaluation stack.
     */
     final val Ldloc_3 = OpCode.Ldloc_3

    /**
<<<<<<< HEAD
     * Pops the current value from the top of the evaluation stack and 
=======
     * Pops the current value from the top of the evaluation stack and
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     * stores it in a the local variable list at index 0.
     */
     final val Stloc_0 = OpCode.Stloc_0

    /**
<<<<<<< HEAD
     * Pops the current value from the top of the evaluation stack and 
=======
     * Pops the current value from the top of the evaluation stack and
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     * stores it in a the local variable list at index 1.
     */
     final val Stloc_1 = OpCode.Stloc_1

    /**
<<<<<<< HEAD
     * Pops the current value from the top of the evaluation stack and 
=======
     * Pops the current value from the top of the evaluation stack and
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     * stores it in a the local variable list at index 2.
     */
     final val Stloc_2 = OpCode.Stloc_2

    /**
<<<<<<< HEAD
     * Pops the current value from the top of the evaluation stack and 
=======
     * Pops the current value from the top of the evaluation stack and
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     * stores it in a the local variable list at index 3.
     */
     final val Stloc_3 = OpCode.Stloc_3

    /**
<<<<<<< HEAD
     * Loads the argument (referenced by a specified short form index) 
=======
     * Loads the argument (referenced by a specified short form index)
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     * onto the evaluation stack.
     */
     final val Ldarg_S = OpCode.Ldarg_S

    /**
     * Load an argument address, in short form, onto the evaluation stack.
     */
     final val Ldarga_S = OpCode.Ldarga_S

    /**
<<<<<<< HEAD
     * Loads the local variable at a specific index onto the evaluation stack, 
=======
     * Loads the local variable at a specific index onto the evaluation stack,
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     * short form.
     */
     final val Ldloc_S = OpCode.Ldloc_S

    /**
<<<<<<< HEAD
     * Loads the address of the local variable at a specific index onto 
=======
     * Loads the address of the local variable at a specific index onto
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     * the evaluation stack, short form.
     */
     final val Ldloca_S = OpCode.Ldloca_S

    /**
<<<<<<< HEAD
     * Stores the value on top of the evaluation stack in the argument slot 
     * at a specified index, short form. 
=======
     * Stores the value on top of the evaluation stack in the argument slot
     * at a specified index, short form.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Starg_S = OpCode.Starg_S

    /**
<<<<<<< HEAD
     * Pops the current value from the top of the evaluation stack and stores it 
     * in a the local variable list at index (short form). 
=======
     * Pops the current value from the top of the evaluation stack and stores it
     * in a the local variable list at index (short form).
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Stloc_S = OpCode.Stloc_S

    /**
<<<<<<< HEAD
     * Pushes a null reference (type O) onto the evaluation stack. 
=======
     * Pushes a null reference (type O) onto the evaluation stack.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Ldnull = OpCode.Ldnull

    /**
<<<<<<< HEAD
     * Pushes the integer value of -1 onto the evaluation stack as an int32.  
=======
     * Pushes the integer value of -1 onto the evaluation stack as an int32.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Ldc_I4_M1 = OpCode.Ldc_I4_M1

    /**
<<<<<<< HEAD
     * Pushes the integer value of 0 onto the evaluation stack as an int32. 
=======
     * Pushes the integer value of 0 onto the evaluation stack as an int32.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Ldc_I4_0 = OpCode.Ldc_I4_0

    /**
<<<<<<< HEAD
     * Pushes the integer value of 1 onto the evaluation stack as an int32. 
=======
     * Pushes the integer value of 1 onto the evaluation stack as an int32.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Ldc_I4_1 = OpCode.Ldc_I4_1

    /**
<<<<<<< HEAD
     * Pushes the integer value of 2 onto the evaluation stack as an int32. 
=======
     * Pushes the integer value of 2 onto the evaluation stack as an int32.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Ldc_I4_2 = OpCode.Ldc_I4_2

    /**
<<<<<<< HEAD
     * Pushes the integer value of 3 onto the evaluation stack as an int32. 
=======
     * Pushes the integer value of 3 onto the evaluation stack as an int32.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Ldc_I4_3 = OpCode.Ldc_I4_3

    /**
<<<<<<< HEAD
     * Pushes the integer value of 4 onto the evaluation stack as an int32.  
=======
     * Pushes the integer value of 4 onto the evaluation stack as an int32.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Ldc_I4_4 = OpCode.Ldc_I4_4

    /**
<<<<<<< HEAD
     * Pushes the integer value of 5 onto the evaluation stack as an int32. 
=======
     * Pushes the integer value of 5 onto the evaluation stack as an int32.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Ldc_I4_5 = OpCode.Ldc_I4_5

    /**
     * Pushes the integer value of 6 onto the evaluation stack as an int32.
     */
     final val Ldc_I4_6 = OpCode.Ldc_I4_6

    /**
<<<<<<< HEAD
     * Pushes the integer value of 7 onto the evaluation stack as an int32. 
=======
     * Pushes the integer value of 7 onto the evaluation stack as an int32.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Ldc_I4_7 = OpCode.Ldc_I4_7

    /**
<<<<<<< HEAD
     * Pushes the integer value of 8 onto the evaluation stack as an int32. 
=======
     * Pushes the integer value of 8 onto the evaluation stack as an int32.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Ldc_I4_8 = OpCode.Ldc_I4_8

    /**
     * Pushes the supplied int8 value onto the evaluation stack as an int32, short form.
     */
     final val Ldc_I4_S = OpCode.Ldc_I4_S

    /**
<<<<<<< HEAD
     * Pushes a supplied value of type int32 onto the evaluation stack as an int32. 
=======
     * Pushes a supplied value of type int32 onto the evaluation stack as an int32.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Ldc_I4 = OpCode.Ldc_I4

    /**
<<<<<<< HEAD
     *  Pushes a supplied value of type int64 onto the evaluation stack as an int64.  
=======
     *  Pushes a supplied value of type int64 onto the evaluation stack as an int64.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Ldc_I8 = OpCode.Ldc_I8

    /**
<<<<<<< HEAD
     * Pushes a supplied value of type float32 onto the evaluation stack as type F (float). 
=======
     * Pushes a supplied value of type float32 onto the evaluation stack as type F (float).
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Ldc_R4 = OpCode.Ldc_R4

    /**
<<<<<<< HEAD
     * Pushes a supplied value of type float64 onto the evaluation stack as type F (float). 
=======
     * Pushes a supplied value of type float64 onto the evaluation stack as type F (float).
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Ldc_R8 = OpCode.Ldc_R8

    /**
<<<<<<< HEAD
     * Copies the current topmost value on the evaluation stack, and then pushes the copy 
     * onto the evaluation stack. 
=======
     * Copies the current topmost value on the evaluation stack, and then pushes the copy
     * onto the evaluation stack.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Dup = OpCode.Dup

    /**
<<<<<<< HEAD
     * Removes the value currently on top of the evaluation stack. 
=======
     * Removes the value currently on top of the evaluation stack.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Pop = OpCode.Pop

    /**
<<<<<<< HEAD
     * Exits current method and jumps to specified method. 
=======
     * Exits current method and jumps to specified method.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Jmp = OpCode.Jmp

    /**
<<<<<<< HEAD
     * Calls the method indicated by the passed method descriptor. 
=======
     * Calls the method indicated by the passed method descriptor.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Call = OpCode.Call

    /**
     * constrained. prefix
     */
     final val Constrained = OpCode.Constrained

    /**
     * readonly. prefix
     */
     final val Readonly = OpCode.Readonly

    /**
     * Calls the method indicated on the evaluation stack (as a pointer to an entry point)
<<<<<<< HEAD
     * with arguments described by a calling convention. 
=======
     * with arguments described by a calling convention.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Calli = OpCode.Calli

    /**
<<<<<<< HEAD
     * Returns from the current method, pushing a return value (if present) from the caller's 
     * evaluation stack onto the callee's evaluation stack. 
=======
     * Returns from the current method, pushing a return value (if present) from the caller's
     * evaluation stack onto the callee's evaluation stack.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Ret = OpCode.Ret

    /**
<<<<<<< HEAD
     * Unconditionally transfers control to a target instruction (short form). 
=======
     * Unconditionally transfers control to a target instruction (short form).
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Br_S = OpCode.Br_S

    /**
<<<<<<< HEAD
     * Transfers control to a target instruction if value is false, a null reference, or zero. 
=======
     * Transfers control to a target instruction if value is false, a null reference, or zero.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Brfalse_S = OpCode.Brfalse_S

    /**
<<<<<<< HEAD
     * Transfers control to a target instruction (short form) if value is true, not null, or non-zero. 
=======
     * Transfers control to a target instruction (short form) if value is true, not null, or non-zero.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Brtrue_S = OpCode.Brtrue_S

    /**
     * Transfers control to a target instruction (short form) if two values are equal.
     */
     final val Beq_S = OpCode.Beq_S

    /**
<<<<<<< HEAD
     * Transfers control to a target instruction (short form) if the first value is greater than 
     * or equal to the second value. 
=======
     * Transfers control to a target instruction (short form) if the first value is greater than
     * or equal to the second value.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Bge_S = OpCode.Bge_S

    /**
<<<<<<< HEAD
     * Transfers control to a target instruction (short form) if the first value is greater than 
     * the second value. 
=======
     * Transfers control to a target instruction (short form) if the first value is greater than
     * the second value.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Bgt_S = OpCode.Bgt_S

    /**
<<<<<<< HEAD
     * Transfers control to a target instruction (short form) if the first value is less than 
=======
     * Transfers control to a target instruction (short form) if the first value is less than
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     * or equal to the second value.
     */
     final val Ble_S = OpCode.Ble_S

    /**
<<<<<<< HEAD
     * Transfers control to a target instruction (short form) if the first value is less than 
     * the second value. 
=======
     * Transfers control to a target instruction (short form) if the first value is less than
     * the second value.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Blt_S = OpCode.Blt_S

    /**
<<<<<<< HEAD
     * Transfers control to a target instruction (short form) when two unsigned integer values 
     * or unordered float values are not equal. 
=======
     * Transfers control to a target instruction (short form) when two unsigned integer values
     * or unordered float values are not equal.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Bne_Un_S = OpCode.Bne_Un_S

    /**
<<<<<<< HEAD
     * Transfers control to a target instruction (short form) if if the the first value is greather 
     * than the second value, when comparing unsigned integer values or unordered float values. 
=======
     * Transfers control to a target instruction (short form) if the first value is greather
     * than the second value, when comparing unsigned integer values or unordered float values.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Bge_Un_S = OpCode.Bge_Un_S

    /**
<<<<<<< HEAD
     * Transfers control to a target instruction (short form) if the first value is greater than 
     * the second value, when comparing unsigned integer values or unordered float values. 
=======
     * Transfers control to a target instruction (short form) if the first value is greater than
     * the second value, when comparing unsigned integer values or unordered float values.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Bgt_Un_S = OpCode.Bgt_Un_S

    /**
<<<<<<< HEAD
     * Transfers control to a target instruction (short form) if the first value is less than 
     * or equal to the second value, when comparing unsigned integer values or unordered float values. 
=======
     * Transfers control to a target instruction (short form) if the first value is less than
     * or equal to the second value, when comparing unsigned integer values or unordered float values.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Ble_Un_S = OpCode.Ble_Un_S

    /**
<<<<<<< HEAD
     * Transfers control to a target instruction (short form) if the first value is less than 
     * the second value, when comparing unsigned integer values or unordered float values. 
=======
     * Transfers control to a target instruction (short form) if the first value is less than
     * the second value, when comparing unsigned integer values or unordered float values.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Blt_Un_S = OpCode.Blt_Un_S

    /**
<<<<<<< HEAD
     * Unconditionally transfers control to a target instruction. 
=======
     * Unconditionally transfers control to a target instruction.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Br = OpCode.Br

    /**
<<<<<<< HEAD
     * Transfers control to a target instruction if value is false, a null reference 
     * (Nothing in Visual Basic), or zero. 
=======
     * Transfers control to a target instruction if value is false, a null reference
     * (Nothing in Visual Basic), or zero.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Brfalse = OpCode.Brfalse

    /**
<<<<<<< HEAD
     * Transfers control to a target instruction if value is true, not null, or non-zero. 
=======
     * Transfers control to a target instruction if value is true, not null, or non-zero.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Brtrue = OpCode.Brtrue

    /**
     * Transfers control to a target instruction if two values are equal.
     */
     final val Beq = OpCode.Beq

    /**
<<<<<<< HEAD
     * Transfers control to a target instruction if the first value is greater than or 
=======
     * Transfers control to a target instruction if the first value is greater than or
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     * equal to the second value.
     */
     final val Bge = OpCode.Bge

    /**
     * Transfers control to a target instruction if the first value is greater than the second value.
     */
     final val Bgt = OpCode.Bgt

    /**
<<<<<<< HEAD
     * Transfers control to a target instruction if the first value is less than or equal 
=======
     * Transfers control to a target instruction if the first value is less than or equal
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     * to the second value.
     */
     final val Ble = OpCode.Ble

    /**
<<<<<<< HEAD
     *  Transfers control to a target instruction if the first value is less than the second value. 
=======
     *  Transfers control to a target instruction if the first value is less than the second value.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Blt = OpCode.Blt

    /**
<<<<<<< HEAD
     * Transfers control to a target instruction when two unsigned integer values or 
     * unordered float values are not equal. 
=======
     * Transfers control to a target instruction when two unsigned integer values or
     * unordered float values are not equal.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Bne_Un = OpCode.Bne_Un

    /**
<<<<<<< HEAD
     * Transfers control to a target instruction if the the first value is greather than 
=======
     * Transfers control to a target instruction if the first value is greather than
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     * the second value, when comparing unsigned integer values or unordered float values.
     */
     final val Bge_Un = OpCode.Bge_Un

    /**
<<<<<<< HEAD
     * Transfers control to a target instruction if the first value is greater than the 
     * second value, when comparing unsigned integer values or unordered float values. 
=======
     * Transfers control to a target instruction if the first value is greater than the
     * second value, when comparing unsigned integer values or unordered float values.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Bgt_Un = OpCode.Bgt_Un

    /**
<<<<<<< HEAD
     * Transfers control to a target instruction if the first value is less than or equal to 
     * the second value, when comparing unsigned integer values or unordered float values. 
=======
     * Transfers control to a target instruction if the first value is less than or equal to
     * the second value, when comparing unsigned integer values or unordered float values.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Ble_Un = OpCode.Ble_Un

    /**
<<<<<<< HEAD
     * Transfers control to a target instruction if the first value is less than the second value, 
     * when comparing unsigned integer values or unordered float values. 
=======
     * Transfers control to a target instruction if the first value is less than the second value,
     * when comparing unsigned integer values or unordered float values.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Blt_Un = OpCode.Blt_Un

    /**
<<<<<<< HEAD
     * Implements a jump table. 
=======
     * Implements a jump table.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Switch = OpCode.Switch

    /**
<<<<<<< HEAD
     * Loads a value of type int8 as an int32 onto the evaluation stack indirectly. 
=======
     * Loads a value of type int8 as an int32 onto the evaluation stack indirectly.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Ldind_I1 = OpCode.Ldind_I1

    /**
     *  Loads a value of type int16 as an int32 onto the evaluation stack indirectly.
     */
     final val Ldind_I2 = OpCode.Ldind_I2

    /**
<<<<<<< HEAD
     * Loads a value of type int32 as an int32 onto the evaluation stack indirectly. 
=======
     * Loads a value of type int32 as an int32 onto the evaluation stack indirectly.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Ldind_I4 = OpCode.Ldind_I4

    /**
<<<<<<< HEAD
     * Loads a value of type int64 as an int64 onto the evaluation stack indirectly. 
=======
     * Loads a value of type int64 as an int64 onto the evaluation stack indirectly.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Ldind_I8 = OpCode.Ldind_I8

    /**
<<<<<<< HEAD
     * Loads a value of type natural int as a natural int onto the evaluation stack indirectly. 
=======
     * Loads a value of type natural int as a natural int onto the evaluation stack indirectly.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Ldind_I = OpCode.Ldind_I

    /**
<<<<<<< HEAD
     *  Loads a value of type float32 as a type F (float) onto the evaluation stack indirectly. 
=======
     *  Loads a value of type float32 as a type F (float) onto the evaluation stack indirectly.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Ldind_R4 = OpCode.Ldind_R4

    /**
<<<<<<< HEAD
     * Loads a value of type float64 as a type F (float) onto the evaluation stack indirectly. 
=======
     * Loads a value of type float64 as a type F (float) onto the evaluation stack indirectly.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Ldind_R8 = OpCode.Ldind_R8

    /**
<<<<<<< HEAD
     * Loads an object reference as a type O (object reference) onto the evaluation stack indirectly. 
=======
     * Loads an object reference as a type O (object reference) onto the evaluation stack indirectly.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Ldind_Ref = OpCode.Ldind_Ref

    /**
<<<<<<< HEAD
     * Loads a value of type unsigned int8 as an int32 onto the evaluation stack indirectly. 
=======
     * Loads a value of type unsigned int8 as an int32 onto the evaluation stack indirectly.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Ldind_U1 = OpCode.Ldind_U1

    /**
<<<<<<< HEAD
     * Loads a value of type unsigned int16 as an int32 onto the evaluation stack indirectly. 
=======
     * Loads a value of type unsigned int16 as an int32 onto the evaluation stack indirectly.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Ldind_U2 = OpCode.Ldind_U2

    /**
<<<<<<< HEAD
     * Loads a value of type unsigned int32 as an int32 onto the evaluation stack indirectly. 
=======
     * Loads a value of type unsigned int32 as an int32 onto the evaluation stack indirectly.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Ldind_U4 = OpCode.Ldind_U4

    /**
<<<<<<< HEAD
     * Stores a object reference value at a supplied address. 
=======
     * Stores a object reference value at a supplied address.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Stind_Ref = OpCode.Stind_Ref

    /**
<<<<<<< HEAD
     * Stores a value of type int8 at a supplied address. 
=======
     * Stores a value of type int8 at a supplied address.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Stind_I1 = OpCode.Stind_I1

    /**
<<<<<<< HEAD
     * Stores a value of type int16 at a supplied address. 
=======
     * Stores a value of type int16 at a supplied address.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Stind_I2 = OpCode.Stind_I2

    /**
<<<<<<< HEAD
     * Stores a value of type int32 at a supplied address. 
=======
     * Stores a value of type int32 at a supplied address.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Stind_I4 = OpCode.Stind_I4

    /**
<<<<<<< HEAD
     * Stores a value of type int64 at a supplied address. 
=======
     * Stores a value of type int64 at a supplied address.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Stind_I8 = OpCode.Stind_I8

    /**
<<<<<<< HEAD
     * Stores a value of type float32 at a supplied address. 
=======
     * Stores a value of type float32 at a supplied address.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Stind_R4 = OpCode.Stind_R4

    /**
<<<<<<< HEAD
     * Stores a value of type float64 at a supplied address. 
=======
     * Stores a value of type float64 at a supplied address.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Stind_R8 = OpCode.Stind_R8

    /**
<<<<<<< HEAD
     * Subtracts one value from another and pushes the result onto the evaluation stack. 
=======
     * Subtracts one value from another and pushes the result onto the evaluation stack.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Sub = OpCode.Sub

    /**
<<<<<<< HEAD
     * Multiplies two values and pushes the result on the evaluation stack. 
=======
     * Multiplies two values and pushes the result on the evaluation stack.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Mul = OpCode.Mul

    /**
<<<<<<< HEAD
     * Divides two values and pushes the result as a floating-point (type F) or 
=======
     * Divides two values and pushes the result as a floating-point (type F) or
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     * quotient (type int32) onto the evaluation stack.
     */
     final val Div = OpCode.Div

    /**
<<<<<<< HEAD
     * Divides two unsigned integer values and pushes the result (int32) onto the evaluation stack. 
=======
     * Divides two unsigned integer values and pushes the result (int32) onto the evaluation stack.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Div_Un = OpCode.Div_Un

    /**
<<<<<<< HEAD
     * Divides two values and pushes the remainder onto the evaluation stack. 
=======
     * Divides two values and pushes the remainder onto the evaluation stack.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Rem = OpCode.Rem

    /**
<<<<<<< HEAD
     * Divides two unsigned values and pushes the remainder onto the evaluation stack. 
=======
     * Divides two unsigned values and pushes the remainder onto the evaluation stack.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Rem_Un = OpCode.Rem_Un

    /**
     * Computes the bitwise AND of two values and pushes the result onto the evaluation stack.
     */
     final val And = OpCode.And

    /**
<<<<<<< HEAD
     * Compute the bitwise complement of the two integer values on top of the stack and 
     * pushes the result onto the evaluation stack. 
=======
     * Compute the bitwise complement of the two integer values on top of the stack and
     * pushes the result onto the evaluation stack.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Or = OpCode.Or

    /**
<<<<<<< HEAD
     * Computes the bitwise XOR of the top two values on the evaluation stack, 
     * pushing the result onto the evaluation stack. 
=======
     * Computes the bitwise XOR of the top two values on the evaluation stack,
     * pushing the result onto the evaluation stack.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Xor = OpCode.Xor

    /**
     * Shifts an integer value to the left (in zeroes) by a specified number of bits,
     *  pushing the result onto the evaluation stack.
     */
     final val Shl = OpCode.Shl

    /**
<<<<<<< HEAD
     * Shifts an integer value (in sign) to the right by a specified number of bits, 
     * pushing the result onto the evaluation stack. 
=======
     * Shifts an integer value (in sign) to the right by a specified number of bits,
     * pushing the result onto the evaluation stack.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Shr = OpCode.Shr

    /**
<<<<<<< HEAD
     * Shifts an unsigned integer value (in zeroes) to the right by a specified number of bits, 
     * pushing the result onto the evaluation stack. 
=======
     * Shifts an unsigned integer value (in zeroes) to the right by a specified number of bits,
     * pushing the result onto the evaluation stack.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Shr_Un = OpCode.Shr_Un

    /**
<<<<<<< HEAD
     * Negates a value and pushes the result onto the evaluation stack. 
=======
     * Negates a value and pushes the result onto the evaluation stack.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Neg = OpCode.Neg

    /**
<<<<<<< HEAD
     * Computes the bitwise complement of the integer value on top of the stack and pushes 
=======
     * Computes the bitwise complement of the integer value on top of the stack and pushes
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     * the result onto the evaluation stack as the same type.
     */
     final val Not = OpCode.Not

    /**
<<<<<<< HEAD
     *  Converts the value on top of the evaluation stack to int8, then extends (pads) it to int32. 
=======
     *  Converts the value on top of the evaluation stack to int8, then extends (pads) it to int32.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Conv_I1 = OpCode.Conv_I1

    /**
<<<<<<< HEAD
     * Converts the value on top of the evaluation stack to int16, then extends (pads) it to int32. 
=======
     * Converts the value on top of the evaluation stack to int16, then extends (pads) it to int32.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Conv_I2 = OpCode.Conv_I2

    /**
<<<<<<< HEAD
     * Converts the value on top of the evaluation stack to int32. 
=======
     * Converts the value on top of the evaluation stack to int32.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Conv_I4 = OpCode.Conv_I4

    /**
<<<<<<< HEAD
     * Converts the value on top of the evaluation stack to int64. 
=======
     * Converts the value on top of the evaluation stack to int64.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Conv_I8 = OpCode.Conv_I8

    /**
<<<<<<< HEAD
     * Converts the value on top of the evaluation stack to float32. 
=======
     * Converts the value on top of the evaluation stack to float32.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Conv_R4 = OpCode.Conv_R4

    /**
<<<<<<< HEAD
     * Converts the value on top of the evaluation stack to float64. 
=======
     * Converts the value on top of the evaluation stack to float64.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Conv_R8 = OpCode.Conv_R8

    /**
<<<<<<< HEAD
     * Converts the value on top of the evaluation stack to unsigned int32, and extends it to int32. 
=======
     * Converts the value on top of the evaluation stack to unsigned int32, and extends it to int32.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Conv_U4 = OpCode.Conv_U4

    /**
<<<<<<< HEAD
     * Converts the value on top of the evaluation stack to unsigned int64, and extends it to int64. 
=======
     * Converts the value on top of the evaluation stack to unsigned int64, and extends it to int64.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Conv_U8 = OpCode.Conv_U8

    /**
<<<<<<< HEAD
     * Calls a late-bound method on an object, pushing the return value onto the evaluation stack. 
=======
     * Calls a late-bound method on an object, pushing the return value onto the evaluation stack.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Callvirt = OpCode.Callvirt

    /**
<<<<<<< HEAD
     * Copies the value type located at the address of an object (type &, * or natural int) 
     * to the address of the destination object (type &, * or natural int). 
=======
     * Copies the value type located at the address of an object (type &, * or natural int)
     * to the address of the destination object (type &, * or natural int).
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Cpobj = OpCode.Cpobj

    /**
<<<<<<< HEAD
     * Copies the value type object pointed to by an address to the top of the evaluation stack. 
=======
     * Copies the value type object pointed to by an address to the top of the evaluation stack.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Ldobj = OpCode.Ldobj

    /**
<<<<<<< HEAD
     * Pushes a new object reference to a string literal stored in the metadata. 
=======
     * Pushes a new object reference to a string literal stored in the metadata.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Ldstr = OpCode.Ldstr

    /**
<<<<<<< HEAD
     * Creates a new object or a new instance of a value type, pushing an object reference 
     * (type O) onto the evaluation stack. 
=======
     * Creates a new object or a new instance of a value type, pushing an object reference
     * (type O) onto the evaluation stack.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Newobj = OpCode.Newobj

    /**
<<<<<<< HEAD
     * Attempts to cast an object passed by reference to the specified class. 
=======
     * Attempts to cast an object passed by reference to the specified class.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Castclass = OpCode.Castclass

    /**
     * Tests whether an object reference (type O) is an instance of a particular class.
     */
     final val Isinst = OpCode.Isinst

    /**
<<<<<<< HEAD
     *  Converts the unsigned integer value on top of the evaluation stack to float32. 
=======
     *  Converts the unsigned integer value on top of the evaluation stack to float32.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Conv_R_Un = OpCode.Conv_R_Un

    /**
<<<<<<< HEAD
     * Converts the boxed representation of a value type to its unboxed form. 
=======
     * Converts the boxed representation of a value type to its unboxed form.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Unbox = OpCode.Unbox

    /**
<<<<<<< HEAD
     * Throws the exception object currently on the evaluation stack. 
=======
     * Throws the exception object currently on the evaluation stack.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Throw = OpCode.Throw

    /**
<<<<<<< HEAD
     *  Finds the value of a field in the object whose reference is currently 
     * on the evaluation stack. 
=======
     *  Finds the value of a field in the object whose reference is currently
     * on the evaluation stack.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Ldfld = OpCode.Ldfld

    /**
<<<<<<< HEAD
     *  Finds the address of a field in the object whose reference is currently 
     * on the evaluation stack. 
=======
     *  Finds the address of a field in the object whose reference is currently
     * on the evaluation stack.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Ldflda = OpCode.Ldflda

    /**
<<<<<<< HEAD
     * Pushes the value of a static field onto the evaluation stack. 
=======
     * Pushes the value of a static field onto the evaluation stack.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Ldsfld = OpCode.Ldsfld

    /**
<<<<<<< HEAD
     * Pushes the address of a static field onto the evaluation stack. 
=======
     * Pushes the address of a static field onto the evaluation stack.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Ldsflda = OpCode.Ldsflda

    /**
<<<<<<< HEAD
     *  Replaces the value stored in the field of an object reference or pointer with a new value. 
=======
     *  Replaces the value stored in the field of an object reference or pointer with a new value.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Stfld = OpCode.Stfld

    /**
<<<<<<< HEAD
     * Replaces the value of a static field with a value from the evaluation stack. 
=======
     * Replaces the value of a static field with a value from the evaluation stack.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Stsfld = OpCode.Stsfld

    /**
<<<<<<< HEAD
     * Copies a value of a specified type from the evaluation stack into a supplied memory address. 
=======
     * Copies a value of a specified type from the evaluation stack into a supplied memory address.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Stobj = OpCode.Stobj

    /**
<<<<<<< HEAD
     * Converts the unsigned value on top of the evaluation stack to signed int8 and 
=======
     * Converts the unsigned value on top of the evaluation stack to signed int8 and
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     * extends it to int32, throwing OverflowException on overflow.
     */
     final val Conv_Ovf_I1_Un = OpCode.Conv_Ovf_I1_Un

    /**
<<<<<<< HEAD
     *  Converts the unsigned value on top of the evaluation stack to signed int16 and 
     * extends it to int32, throwing OverflowException on overflow. 
=======
     *  Converts the unsigned value on top of the evaluation stack to signed int16 and
     * extends it to int32, throwing OverflowException on overflow.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Conv_Ovf_I2_Un = OpCode.Conv_Ovf_I2_Un

    /**
<<<<<<< HEAD
     * Converts the unsigned value on top of the evaluation stack to signed int32, 
     * throwing OverflowException on overflow. 
=======
     * Converts the unsigned value on top of the evaluation stack to signed int32,
     * throwing OverflowException on overflow.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Conv_Ovf_I4_Un = OpCode.Conv_Ovf_I4_Un

    /**
<<<<<<< HEAD
     * Converts the unsigned value on top of the evaluation stack to signed int64, 
     * throwing OverflowException on overflow. 
=======
     * Converts the unsigned value on top of the evaluation stack to signed int64,
     * throwing OverflowException on overflow.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Conv_Ovf_I8_Un = OpCode.Conv_Ovf_I8_Un

    /**
<<<<<<< HEAD
     * Converts the unsigned value on top of the evaluation stack to signed natural int, 
     * throwing OverflowException on overflow. 
=======
     * Converts the unsigned value on top of the evaluation stack to signed natural int,
     * throwing OverflowException on overflow.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Conv_Ovf_I_Un = OpCode.Conv_Ovf_I_Un

    /**
<<<<<<< HEAD
     * Converts the unsigned value on top of the evaluation stack to unsigned int8 and 
     * extends it to int32, throwing OverflowException on overflow. 
=======
     * Converts the unsigned value on top of the evaluation stack to unsigned int8 and
     * extends it to int32, throwing OverflowException on overflow.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Conv_Ovf_U1_Un = OpCode.Conv_Ovf_U1_Un

    /**
<<<<<<< HEAD
     * Converts the unsigned value on top of the evaluation stack to unsigned int16 and 
     * extends it to int32, throwing OverflowException on overflow. 
=======
     * Converts the unsigned value on top of the evaluation stack to unsigned int16 and
     * extends it to int32, throwing OverflowException on overflow.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Conv_Ovf_U2_Un = OpCode.Conv_Ovf_U2_Un

    /**
<<<<<<< HEAD
     * Converts the unsigned value on top of the evaluation stack to unsigned int32, 
     * throwing OverflowException on overflow. 
=======
     * Converts the unsigned value on top of the evaluation stack to unsigned int32,
     * throwing OverflowException on overflow.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Conv_Ovf_U4_Un = OpCode.Conv_Ovf_U4_Un

    /**
<<<<<<< HEAD
     * Converts the unsigned value on top of the evaluation stack to unsigned int64, 
     * throwing OverflowException on overflow. 
=======
     * Converts the unsigned value on top of the evaluation stack to unsigned int64,
     * throwing OverflowException on overflow.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Conv_Ovf_U8_Un = OpCode.Conv_Ovf_U8_Un

    /**
<<<<<<< HEAD
     * Converts the unsigned value on top of the evaluation stack to unsigned natural int, 
     * throwing OverflowException on overflow. 
=======
     * Converts the unsigned value on top of the evaluation stack to unsigned natural int,
     * throwing OverflowException on overflow.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Conv_Ovf_U_Un = OpCode.Conv_Ovf_U_Un

    /**
     * Converts a value type to an object reference (type O).
     */
     final val Box = OpCode.Box

    /**
<<<<<<< HEAD
     * Pushes an object reference to a new zero-based, one-dimensional array whose elements 
     * are of a specific type onto the evaluation stack. 
=======
     * Pushes an object reference to a new zero-based, one-dimensional array whose elements
     * are of a specific type onto the evaluation stack.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Newarr = OpCode.Newarr

    /**
<<<<<<< HEAD
     * Pushes the number of elements of a zero-based, one-dimensional array 
     * onto the evaluation stack. 
=======
     * Pushes the number of elements of a zero-based, one-dimensional array
     * onto the evaluation stack.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Ldlen = OpCode.Ldlen

    /**
<<<<<<< HEAD
     * Loads the address of the array element at a specified array index onto 
=======
     * Loads the address of the array element at a specified array index onto
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     * the top of the evaluation stack as type & (managed pointer).
     */
     final val Ldelema = OpCode.Ldelema

    /**
<<<<<<< HEAD
     * Loads the element with type natural int at a specified array index onto the top 
     * of the evaluation stack as a natural int. 
=======
     * Loads the element with type natural int at a specified array index onto the top
     * of the evaluation stack as a natural int.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Ldelem_I = OpCode.Ldelem_I

    /**
<<<<<<< HEAD
     * Loads the element with type int8 at a specified array index onto the top of the 
     * evaluation stack as an int32. 
=======
     * Loads the element with type int8 at a specified array index onto the top of the
     * evaluation stack as an int32.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Ldelem_I1 = OpCode.Ldelem_I1

    /**
<<<<<<< HEAD
     * Loads the element with type int16 at a specified array index onto the top of 
     * the evaluation stack as an int32. 
=======
     * Loads the element with type int16 at a specified array index onto the top of
     * the evaluation stack as an int32.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Ldelem_I2 = OpCode.Ldelem_I2

    /**
<<<<<<< HEAD
     *  Loads the element with type int32 at a specified array index onto the top of the 
     * evaluation stack as an int32.  
=======
     *  Loads the element with type int32 at a specified array index onto the top of the
     * evaluation stack as an int32.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Ldelem_I4 = OpCode.Ldelem_I4

    /**
<<<<<<< HEAD
     *  Loads the element with type int64 at a specified array index onto the top of the 
=======
     *  Loads the element with type int64 at a specified array index onto the top of the
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     * evaluation stack as an int64.
     */
     final val Ldelem_I8 = OpCode.Ldelem_I8

    /**
<<<<<<< HEAD
     * Loads the element with type float32 at a specified array index onto the top of the 
     * evaluation stack as type F (float) 
=======
     * Loads the element with type float32 at a specified array index onto the top of the
     * evaluation stack as type F (float)
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Ldelem_R4 = OpCode.Ldelem_R4

    /**
<<<<<<< HEAD
     * Loads the element with type float64 at a specified array index onto the top of the 
=======
     * Loads the element with type float64 at a specified array index onto the top of the
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     * evaluation stack as type F (float) .
     */
     final val Ldelem_R8 = OpCode.Ldelem_R8

    /**
<<<<<<< HEAD
     * Loads the element containing an object reference at a specified array index onto 
     * the top of the evaluation stack as type O (object reference). 
=======
     * Loads the element containing an object reference at a specified array index onto
     * the top of the evaluation stack as type O (object reference).
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Ldelem_Ref = OpCode.Ldelem_Ref

    /**
<<<<<<< HEAD
     * Loads the element with type unsigned int8 at a specified array index onto the top 
     * of the evaluation stack as an int32.  
=======
     * Loads the element with type unsigned int8 at a specified array index onto the top
     * of the evaluation stack as an int32.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Ldelem_U1 = OpCode.Ldelem_U1

    /**
<<<<<<< HEAD
     * Loads the element with type unsigned int16 at a specified array index onto the top 
=======
     * Loads the element with type unsigned int16 at a specified array index onto the top
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     * of the evaluation stack as an int32.
     */
     final val Ldelem_U2 = OpCode.Ldelem_U2

    /**
<<<<<<< HEAD
     * Loads the element with type unsigned int32 at a specified array index onto the top 
     * of the evaluation stack as an int32.  
=======
     * Loads the element with type unsigned int32 at a specified array index onto the top
     * of the evaluation stack as an int32.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Ldelem_U4 = OpCode.Ldelem_U4

    /**
<<<<<<< HEAD
     *  Replaces the array element at a given index with the natural int value on 
     * the evaluation stack. 
=======
     *  Replaces the array element at a given index with the natural int value on
     * the evaluation stack.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Stelem_I = OpCode.Stelem_I

    /**
<<<<<<< HEAD
     * Replaces the array element at a given index with the int8 value on the evaluation stack. 
=======
     * Replaces the array element at a given index with the int8 value on the evaluation stack.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Stelem_I1 = OpCode.Stelem_I1

    /**
<<<<<<< HEAD
     *  Replaces the array element at a given index with the int16 value on the evaluation stack. 
=======
     *  Replaces the array element at a given index with the int16 value on the evaluation stack.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Stelem_I2 = OpCode.Stelem_I2

    /**
<<<<<<< HEAD
     *  Replaces the array element at a given index with the int32 value on the evaluation stack. 
=======
     *  Replaces the array element at a given index with the int32 value on the evaluation stack.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Stelem_I4 = OpCode.Stelem_I4

    /**
<<<<<<< HEAD
     * Replaces the array element at a given index with the int64 value on the evaluation stack. 
=======
     * Replaces the array element at a given index with the int64 value on the evaluation stack.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Stelem_I8 = OpCode.Stelem_I8

    /**
<<<<<<< HEAD
     * Replaces the array element at a given index with the float32 value on the evaluation stack. 
=======
     * Replaces the array element at a given index with the float32 value on the evaluation stack.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Stelem_R4 = OpCode.Stelem_R4

    /**
<<<<<<< HEAD
     * Replaces the array element at a given index with the float64 value on the evaluation stack. 
=======
     * Replaces the array element at a given index with the float64 value on the evaluation stack.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Stelem_R8 = OpCode.Stelem_R8

    /**
<<<<<<< HEAD
     * Replaces the array element at a given index with the object ref value (type O) 
     * on the evaluation stack. 
=======
     * Replaces the array element at a given index with the object ref value (type O)
     * on the evaluation stack.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Stelem_Ref = OpCode.Stelem_Ref

    /**
<<<<<<< HEAD
     * Converts the signed value on top of the evaluation stack to signed int8 and 
     * extends it to int32, throwing OverflowException on overflow. 
=======
     * Converts the signed value on top of the evaluation stack to signed int8 and
     * extends it to int32, throwing OverflowException on overflow.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Conv_Ovf_I1 = OpCode.Conv_Ovf_I1

    /**
<<<<<<< HEAD
     * Converts the signed value on top of the evaluation stack to signed int16 and 
     * extending it to int32, throwing OverflowException on overflow. 
=======
     * Converts the signed value on top of the evaluation stack to signed int16 and
     * extending it to int32, throwing OverflowException on overflow.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Conv_Ovf_I2 = OpCode.Conv_Ovf_I2

    /**
<<<<<<< HEAD
     * Converts the signed value on top of the evaluation stack to signed int32, 
     * throwing OverflowException on overflow. 
=======
     * Converts the signed value on top of the evaluation stack to signed int32,
     * throwing OverflowException on overflow.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Conv_Ovf_I4 = OpCode.Conv_Ovf_I4

    /**
<<<<<<< HEAD
     * Converts the signed value on top of the evaluation stack to signed int64, 
     * throwing OverflowException on overflow. 
=======
     * Converts the signed value on top of the evaluation stack to signed int64,
     * throwing OverflowException on overflow.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Conv_Ovf_I8 = OpCode.Conv_Ovf_I8

    /**
<<<<<<< HEAD
     * Converts the signed value on top of the evaluation stack to unsigned int8 and 
     * extends it to int32, throwing OverflowException on overflow. 
=======
     * Converts the signed value on top of the evaluation stack to unsigned int8 and
     * extends it to int32, throwing OverflowException on overflow.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Conv_Ovf_U1 = OpCode.Conv_Ovf_U1

    /**
<<<<<<< HEAD
     * Converts the signed value on top of the evaluation stack to unsigned int16 and 
     * extends it to int32, throwing OverflowException on overflow. 
=======
     * Converts the signed value on top of the evaluation stack to unsigned int16 and
     * extends it to int32, throwing OverflowException on overflow.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Conv_Ovf_U2 = OpCode.Conv_Ovf_U2

    /**
<<<<<<< HEAD
     *  Converts the signed value on top of the evaluation stack to unsigned int32, 
     * throwing OverflowException on overflow. 
=======
     *  Converts the signed value on top of the evaluation stack to unsigned int32,
     * throwing OverflowException on overflow.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Conv_Ovf_U4 = OpCode.Conv_Ovf_U4

    /**
<<<<<<< HEAD
     * Converts the signed value on top of the evaluation stack to unsigned int64, 
     * throwing OverflowException on overflow. 
=======
     * Converts the signed value on top of the evaluation stack to unsigned int64,
     * throwing OverflowException on overflow.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Conv_Ovf_U8 = OpCode.Conv_Ovf_U8

    /**
<<<<<<< HEAD
     *  Retrieves the address (type &) embedded in a typed reference. 
=======
     *  Retrieves the address (type &) embedded in a typed reference.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Refanyval = OpCode.Refanyval

    /**
<<<<<<< HEAD
     * Retrieves the type token embedded in a typed reference .  
=======
     * Retrieves the type token embedded in a typed reference .
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Refanytype = OpCode.Refanytype

    /**
     * Throws ArithmeticException if value is not a finite number.
     */
     final val Ckfinite = OpCode.Ckfinite

    /**
<<<<<<< HEAD
     * Pushes a typed reference to an instance of a specific type onto the evaluation stack. 
=======
     * Pushes a typed reference to an instance of a specific type onto the evaluation stack.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Mkrefany = OpCode.Mkrefany

    /**
<<<<<<< HEAD
     * Converts a metadata token to its runtime representation, pushing it onto the evaluation stack. 
=======
     * Converts a metadata token to its runtime representation, pushing it onto the evaluation stack.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Ldtoken = OpCode.Ldtoken

    /**
<<<<<<< HEAD
     * Converts the value on top of the evaluation stack to unsigned int8, and extends it to int32. 
=======
     * Converts the value on top of the evaluation stack to unsigned int8, and extends it to int32.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Conv_U1 = OpCode.Conv_U1

    /**
<<<<<<< HEAD
     * Converts the value on top of the evaluation stack to unsigned int16, and extends it to int32. 
=======
     * Converts the value on top of the evaluation stack to unsigned int16, and extends it to int32.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Conv_U2 = OpCode.Conv_U2

    /**
<<<<<<< HEAD
     * Converts the value on top of the evaluation stack to natural int. 
=======
     * Converts the value on top of the evaluation stack to natural int.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Conv_I = OpCode.Conv_I

    /**
<<<<<<< HEAD
     * Converts the signed value on top of the evaluation stack to signed natural int, 
=======
     * Converts the signed value on top of the evaluation stack to signed natural int,
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     * throwing OverflowException on overflow.
     */
     final val Conv_Ovf_I = OpCode.Conv_Ovf_I

    /**
<<<<<<< HEAD
     * Converts the signed value on top of the evaluation stack to unsigned natural int, 
=======
     * Converts the signed value on top of the evaluation stack to unsigned natural int,
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     * throwing OverflowException on overflow.
     */
     final val Conv_Ovf_U = OpCode.Conv_Ovf_U

    /**
<<<<<<< HEAD
     * Adds two integers, performs an overflow check, and pushes the result 
     * onto the evaluation stack. 
=======
     * Adds two integers, performs an overflow check, and pushes the result
     * onto the evaluation stack.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Add_Ovf = OpCode.Add_Ovf

    /**
<<<<<<< HEAD
     *  Adds two unsigned integer values, performs an overflow check, and pushes the result 
     * onto the evaluation stack. 
=======
     *  Adds two unsigned integer values, performs an overflow check, and pushes the result
     * onto the evaluation stack.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Add_Ovf_Un = OpCode.Add_Ovf_Un

    /**
<<<<<<< HEAD
     * Multiplies two integer values, performs an overflow check, and pushes the result 
     * onto the evaluation stack. 
=======
     * Multiplies two integer values, performs an overflow check, and pushes the result
     * onto the evaluation stack.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Mul_Ovf = OpCode.Mul_Ovf

    /**
<<<<<<< HEAD
     * Multiplies two unsigned integer values , performs an overflow check , 
     * and pushes the result onto the evaluation stack. 
=======
     * Multiplies two unsigned integer values , performs an overflow check ,
     * and pushes the result onto the evaluation stack.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Mul_Ovf_Un = OpCode.Mul_Ovf_Un

    /**
<<<<<<< HEAD
     * Subtracts one integer value from another, performs an overflow check, 
     * and pushes the result onto the evaluation stack. 
=======
     * Subtracts one integer value from another, performs an overflow check,
     * and pushes the result onto the evaluation stack.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Sub_Ovf = OpCode.Sub_Ovf

    /**
<<<<<<< HEAD
     * Subtracts one unsigned integer value from another, performs an overflow check, 
     * and pushes the result onto the evaluation stack. 
=======
     * Subtracts one unsigned integer value from another, performs an overflow check,
     * and pushes the result onto the evaluation stack.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Sub_Ovf_Un = OpCode.Sub_Ovf_Un

    /**
<<<<<<< HEAD
     * Transfers control from the fault or finally clause of an exception block back to 
     * the Common Language Infrastructure (CLI) exception handler. 
=======
     * Transfers control from the fault or finally clause of an exception block back to
     * the Common Language Infrastructure (CLI) exception handler.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Endfinally = OpCode.Endfinally

    /**
<<<<<<< HEAD
     * Exits a protected region of code, unconditionally tranferring control 
     * to a specific target instruction. 
=======
     * Exits a protected region of code, unconditionally tranferring control
     * to a specific target instruction.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Leave = OpCode.Leave

    /**
<<<<<<< HEAD
     * Exits a protected region of code, unconditionally tranferring control 
     * to a target instruction (short form). 
=======
     * Exits a protected region of code, unconditionally tranferring control
     * to a target instruction (short form).
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Leave_S = OpCode.Leave_S

    /**
<<<<<<< HEAD
     * Stores a value of type natural int at a supplied address. 
=======
     * Stores a value of type natural int at a supplied address.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Stind_I = OpCode.Stind_I

    /**
<<<<<<< HEAD
     *  Converts the value on top of the evaluation stack to unsigned natural int, 
     * and extends it to natural int. 
=======
     *  Converts the value on top of the evaluation stack to unsigned natural int,
     * and extends it to natural int.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Conv_U = OpCode.Conv_U

    /**
<<<<<<< HEAD
     * Returns an unmanaged pointer to the argument list of the current method. 
=======
     * Returns an unmanaged pointer to the argument list of the current method.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Arglist = OpCode.Arglist

    /**
<<<<<<< HEAD
     * Compares two values. If they are equal, the integer value 1 (int32) is pushed 
     * onto the evaluation stack otherwise 0 (int32) is pushed onto the evaluation stack. 
=======
     * Compares two values. If they are equal, the integer value 1 (int32) is pushed
     * onto the evaluation stack otherwise 0 (int32) is pushed onto the evaluation stack.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Ceq = OpCode.Ceq

    /**
<<<<<<< HEAD
     * Compares two values. If the first value is greater than the second, 
     * the integer value 1 (int32) is pushed onto the evaluation stack 
     * otherwise 0 (int32) is pushed onto the evaluation stack. 
=======
     * Compares two values. If the first value is greater than the second,
     * the integer value 1 (int32) is pushed onto the evaluation stack
     * otherwise 0 (int32) is pushed onto the evaluation stack.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Cgt = OpCode.Cgt

    /**
<<<<<<< HEAD
     *  Compares two unsigned or unordered values. If the first value is greater than 
     * the second, the integer value 1 (int32) is pushed onto the evaluation stack 
     * otherwise 0 (int32) is pushed onto the evaluation stack. 
=======
     *  Compares two unsigned or unordered values. If the first value is greater than
     * the second, the integer value 1 (int32) is pushed onto the evaluation stack
     * otherwise 0 (int32) is pushed onto the evaluation stack.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Cgt_Un = OpCode.Cgt_Un

    /**
<<<<<<< HEAD
     * Compares two values. If the first value is less than the second, 
     * the integer value 1 (int32) is pushed onto the evaluation stack 
     * otherwise 0 (int32) is pushed onto the evaluation stack. 
=======
     * Compares two values. If the first value is less than the second,
     * the integer value 1 (int32) is pushed onto the evaluation stack
     * otherwise 0 (int32) is pushed onto the evaluation stack.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Clt = OpCode.Clt

    /**
<<<<<<< HEAD
     *  Compares the unsigned or unordered values value1 and value2. If value1 is 
     * less than value2, then the integer value 1 (int32) is pushed onto the 
     * evaluation stack otherwise 0 (int32) is pushed onto the evaluation stack. 
=======
     *  Compares the unsigned or unordered values value1 and value2. If value1 is
     * less than value2, then the integer value 1 (int32) is pushed onto the
     * evaluation stack otherwise 0 (int32) is pushed onto the evaluation stack.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Clt_Un = OpCode.Clt_Un

    /**
<<<<<<< HEAD
     * Pushes an unmanaged pointer (type natural int) to the native code implementing 
     * a specific method onto the evaluation stack. 
=======
     * Pushes an unmanaged pointer (type natural int) to the native code implementing
     * a specific method onto the evaluation stack.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Ldftn = OpCode.Ldftn

    /**
<<<<<<< HEAD
     * Pushes an unmanaged pointer (type natural int) to the native code implementing 
     * a particular virtual method associated with a specified object onto the evaluation stack. 
=======
     * Pushes an unmanaged pointer (type natural int) to the native code implementing
     * a particular virtual method associated with a specified object onto the evaluation stack.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Ldvirtftn = OpCode.Ldvirtftn

    /**
<<<<<<< HEAD
     * Loads an argument (referenced by a specified index value) onto the stack. 
=======
     * Loads an argument (referenced by a specified index value) onto the stack.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Ldarg = OpCode.Ldarg

    /**
<<<<<<< HEAD
     * Load an argument address onto the evaluation stack. 
=======
     * Load an argument address onto the evaluation stack.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Ldarga = OpCode.Ldarga

    /**
<<<<<<< HEAD
     * Loads the local variable at a specific index onto the evaluation stack. 
=======
     * Loads the local variable at a specific index onto the evaluation stack.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Ldloc = OpCode.Ldloc

    /**
<<<<<<< HEAD
     *  Loads the address of the local variable at a specific index onto the evaluation stack. 
=======
     *  Loads the address of the local variable at a specific index onto the evaluation stack.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Ldloca = OpCode.Ldloca

    /**
<<<<<<< HEAD
     *  Stores the value on top of the evaluation stack in the argument slot at a specified index. 
=======
     *  Stores the value on top of the evaluation stack in the argument slot at a specified index.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Starg = OpCode.Starg

    /**
<<<<<<< HEAD
     * Pops the current value from the top of the evaluation stack and stores it in a 
     * the local variable list at a specified index. 
=======
     * Pops the current value from the top of the evaluation stack and stores it in a
     * the local variable list at a specified index.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Stloc = OpCode.Stloc

    /**
<<<<<<< HEAD
     * Allocates a certain number of bytes from the local dynamic memory pool and pushes the 
=======
     * Allocates a certain number of bytes from the local dynamic memory pool and pushes the
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     * address (a transient pointer, type *) of the first allocated Byte onto the evaluation stack.
     */
     final val Localloc = OpCode.Localloc

    /**
<<<<<<< HEAD
     * Transfers control from the filter clause of an exception back to the 
     * Common Language Infrastructure (CLI) exception handler. 
=======
     * Transfers control from the filter clause of an exception back to the
     * Common Language Infrastructure (CLI) exception handler.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Endfilter = OpCode.Endfilter

    /**
<<<<<<< HEAD
     * Indicates that an address currently atop the evaluation stack might not be aligned 
     * to the natural size of the immediately following ldind, stind, ldfld, stfld, ldobj, 
     * stobj, initblk, or cpblk instruction. 
=======
     * Indicates that an address currently atop the evaluation stack might not be aligned
     * to the natural size of the immediately following ldind, stind, ldfld, stfld, ldobj,
     * stobj, initblk, or cpblk instruction.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Unaligned = OpCode.Unaligned

    /**
<<<<<<< HEAD
     * Specifies that an address currently atop the evaluation stack might be volatile, 
     * and the results of reading that location cannot be cached or that multiple stores 
     * to that location cannot be suppressed. 
=======
     * Specifies that an address currently atop the evaluation stack might be volatile,
     * and the results of reading that location cannot be cached or that multiple stores
     * to that location cannot be suppressed.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Volatile = OpCode.Volatile

    /**
<<<<<<< HEAD
     * Performs a postfixed method call instruction such that the current method's stack 
     * frame is removed before the actual call instruction is executed. 
=======
     * Performs a postfixed method call instruction such that the current method's stack
     * frame is removed before the actual call instruction is executed.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Tailcall = OpCode.Tailcall

    /**
<<<<<<< HEAD
     * Initializes all the fields of the object at a specific address to a null reference 
     * or a 0 of the appropriate primitive type. 
=======
     * Initializes all the fields of the object at a specific address to a null reference
     * or a 0 of the appropriate primitive type.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Initobj = OpCode.Initobj

    /**
<<<<<<< HEAD
     * Copies a specified number bytes from a source address to a destination address .  
=======
     * Copies a specified number bytes from a source address to a destination address .
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Cpblk = OpCode.Cpblk

    /**
<<<<<<< HEAD
     * Initializes a specified block of memory at a specific address to a given size 
     * and initial value. 
=======
     * Initializes a specified block of memory at a specific address to a given size
     * and initial value.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Initblk = OpCode.Initblk

    /**
<<<<<<< HEAD
     * Rethrows the current exception. 
=======
     * Rethrows the current exception.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
     */
     final val Rethrow = OpCode.Rethrow

    /**
     * Pushes the size, in bytes, of a supplied value type onto the evaluation stack.
     */
     final val Sizeof = OpCode.Sizeof

    //##########################################################################
}

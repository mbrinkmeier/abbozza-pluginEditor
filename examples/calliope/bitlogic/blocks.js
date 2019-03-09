/**
 * In this file the blocks for bitwise operations are defined.
 */

/**
 * This block is for binary bitwise operations.
 * It has two inputs - LEFT and RIGHT - for NUMBERS.
 * The operation - AND, OR or XOR - is chosen by a dropdown menu.
 */
Blockly.Blocks['bitlogic_operation'] = {
   init: function() {
      this.setHelpUrl(Abbozza.HELP_URL);
      this.setColour(ColorMgr.getCatColor("cat.MATH"));
	   this.setOutput(true,"NUMBER");
    	this.setInputsInline(true);
    	this.setTooltip(_("bitlogic.op_tooltip"));
      this.setPreviousStatement(false);
      this.setNextStatement(false);

	   // Value inputs are inputs to which blocks have to be connected.
	   this.appendValueInput("LEFT").setCheck("NUMBER");
   	this.appendValueInput("RIGHT").setCheck("NUMBER")
        .appendField(new Blockly.FieldDropdown([["AND", "&"], ["OR", "|"], ["XOR", "^"]]), "OP");
   	  // The dropdown menu is given by an array.
   	  // Each entry consists of two components:
   	  // The first is the displayed text displayed in the block.
   	  // The second is the the code for the operator.
   },

	/**
	 * This operation generates the code for thsi block.
	 */
   generateCode : function(generator) {
  	   // Generate the code for both value inputs
  	   var left = generator.valueToCode(this,"LEFT");
  	   var right = generator.valueToCode(this,"RIGHT");

  	   // Generate the code for the field.
  	   var op = generator.fieldToCode(this,"OP");

  	   // Return the generated code.
  	   return "(" + left  + " " + op + " " + right + ")";
  }
}


/**
 * This block represents a bitwise negation
 */
Blockly.Blocks['bitlogic_not'] = {
   init: function() {
      this.setHelpUrl(Abbozza.HELP_URL);
      this.setColour(ColorMgr.getCatColor("cat.MATH"));
	   this.setOutput(true,"NUMBER");
    	this.setInputsInline(true);
    	this.setTooltip(_("bitlogic.not_tooltip"));
      this.setPreviousStatement(false);
      this.setNextStatement(false);

      // A value input with a leading text.
	   this.appendValueInput("VALUE").setCheck("NUMBER").appendField("NOT");
  },
	
  generateCode : function(generator) {
  	var left = generator.valueToCode(this,"VALUE");
  	return "~" + left;
  }
}


/**
 * This block is for bitwise shifts.
 */
Blockly.Blocks['bitlogic_shift'] = {
   init: function() {
      this.setHelpUrl(Abbozza.HELP_URL);
      this.setColour(ColorMgr.getCatColor("cat.MATH"));
	   this.setOutput(true,"NUMBER");
    	this.setInputsInline(true);
    	this.setTooltip(_("bitlogic.shift_tooltip"));
      this.setPreviousStatement(false);
      this.setNextStatement(false);

	   // Value inputs are inputs to which blocks have to be connected.
	   this.appendValueInput("VALUE").setCheck("NUMBER");
   	this.appendValueInput("SHIFT").setCheck("NUMBER")
   	  // The dropdown menu is given by an array.
   	  // Each entry consists of two components:
   	  // The first is the displayed text displayed in the block.
   	  // The second is the the code for the operator.
        .appendField(new Blockly.FieldDropdown([["<<", "<<"], [">>", ">>"]]), "DIR");
   },

	/**
	 * This operation generates the code for thsi block.
	 */
   generateCode : function(generator) {
  	   // Generate the code for both value inputs
  	   var val = generator.valueToCode(this,"VALUE");
  	   var shift = generator.valueToCode(this,"SHIFT");

  	   // Generate the code for the field.
  	   var dir = generator.fieldToCode(this,"DIR");

  	   // Return the generated code.
  	   return "(" + value  + " " + dir + " " + shift + ")";
  }
}
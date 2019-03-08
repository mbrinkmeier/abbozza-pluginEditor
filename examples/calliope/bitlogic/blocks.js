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
	   this.appendValueInput("LEFT").setCheck("NUMBER");
   	this.appendValueInput("RIGHT").setCheck("NUMBER")
        .appendField(new Blockly.FieldDropdown([["AND", "&"], ["OR", "|"], ["XOR", "^"]]), "OP");
    	this.setInputsInline(true);
    	this.setTooltip(_("bitlogic.op_tooltip"));
      this.setPreviousStatement(false);
      this.setNextStatement(false);
  },
	
  generateCode : function(generator) {
  	var left = generator.valueToCode(this,"LEFT");
  	var right = generator.valueToCode(this,"RIGHT");
  	var op = generator.fieldToCode(this,"OP");
  	return "(" + left  + " " + op + " " + right + ")";
  }
}


Blockly.Blocks['bitlogic_not'] = {
   init: function() {
      this.setHelpUrl(Abbozza.HELP_URL);
      this.setColour(ColorMgr.getCatColor("cat.MATH"));
	   this.setOutput(true,"NUMBER");
	   this.appendValueInput("LEFT").setCheck("NUMBER")
			.appendField("NOT");
    	this.setInputsInline(true);
    	this.setTooltip(_("bitlogic.not_tooltip"));
      this.setPreviousStatement(false);
      this.setNextStatement(false);
  },
	
  generateCode : function(generator) {
  	var left = generator.valueToCode(this,"LEFT");
  	return "~" + left;
  }
}

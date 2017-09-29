# Digital Systems VHDL Arcade Game

This project was undertaken as part of my Digital Systems module in my 4th year studies. 

This project creates a basic version of the retro space invader game. 

It is wrote in VHDL using the Xilinx Spartan 3e FPGA development board. My project was able to map bitmap sprite to a VGA display, detect collisions between different sprites, move sprites around the screen, accept user input from a rotary encoder and tactile switches.

While creating this game I found spirte arrays were complex to manually construct for 8x8 and 16x16 pixel maps and vitually 
impossible to create larger bit maps by hand. With this I created an Excel application to create different types of sprite 
maps. This was done using the Visual Basic (Marcos) scripting langauge that is part of the Excel software package. 

These excel applications generated a csv file with information on the colour of each pixel (cell) in excel. This csv file 
would then be inputted into a Java application to prefix/postfix VHDL code to the bit arrays to automate the generation of
sprite maps to import into my VHDL code. 

This project was completed over a 5 week period. 

**NOTE: I did not write the sync.vdl code that is present in this repo, this was a 3rd party module that was given to me for the purpose            of this project.  

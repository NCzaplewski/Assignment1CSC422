/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package petdatabase;

//Invalid Age Exception 
class InvalidAgeException extends Exception {	


        /**
         * 
         */
        private static final long serialVersionUID = -6131229554476920018L;

        public InvalidAgeException() {
                super("Invalid entry");
        }
}

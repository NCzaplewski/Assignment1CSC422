/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package petdatabase;

class InvalidIDException extends Exception {	

        /**
         * 
         */
        private static final long serialVersionUID = -3369046955835045243L;

        public InvalidIDException() {
                super("Invalid entry.");
        }
}
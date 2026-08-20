class InvalidNameException extends Exception {
    public InvalidNameException(String message){
        super(message);
    }
}
class InvalidAddressException extends Exception{
    public InvalidAddressException(String message){
        super(message);
    }
}
class InvalidContactNumberException extends Exception{
    public InvalidContactNumberException(String message){

        super(message);
    }
}
/* about this class
purpose:----> validate address and contact number
usage: -----> triggered when invalid data is provided
 */


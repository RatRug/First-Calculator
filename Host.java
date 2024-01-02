import java.awt.Font;
import java.util.Scanner;
import java.util.Stack;
import javax.swing.*;
public class Host {
    //TextBox
    static JTextField text;
    static Stack<Double> answers=new Stack<>();
    public static void main(String[] args) {
    //creates the JFrame and calls method to make the calculator
    JFrame frame= new JFrame();
    CreateWindow(frame);
    

    }

    private static void CreateWindow(JFrame frame) {
        //Creates the display text box
        text= new JTextField();
        text.setBounds(0, 0, 415, 100);
        text.setEditable(false);
        text.setFont(new Font( "SANS_SERIF",  Font.BOLD, 100));
        frame.add(text);


        // Adds all of the buttons to the calculator
        int num=-4;
        for(int i=1; i<6; i++){
            for(int j=0; j<4; j++){
                num++;
                    JButton b= new JButton();
                    b.setBounds(j*100, i*100, 100, 100);
                switch(num){
                    case -3: b.setText("CE"); break;
                    case -2: b.setText("Ans");  break;
                    case -1: b.setText("<-"); break;
                    case 0: b.setText("="); break;
                    case 1: b.setText("1");break;
                    case 2: b.setText("2");break;
                    case 3: b.setText("3");break;
                    case 4: b.setText("+");break;
                    case 5: b.setText("4");break;
                    case 6: b.setText("5");break;
                    case 7: b.setText("6");break;
                    case 8: b.setText("—");break;
                    case 9: b.setText("7");break;
                    case 10: b.setText("8");break;
                    case 11: b.setText("9");break;
                    case 12: b.setText("x");break;
                    case 13: b.setText("0");break;
                    case 14: b.setText(".");break;
                    case 15: b.setText("-");break;
                    case 16: b.setText("÷");break;
                }b.setFont(new Font( "SANS_SERIF",  Font.BOLD, 30));
                frame.add(b);
                //adds an actionlistener to read button clicks and sends in terminal what buttons are clicked
                b.addActionListener(e -> System.out.println(newmethod(b)));
                
            }
        }
        //creates the window
        frame.setSize(415,630);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }

    private static Object newmethod(JButton b) {
        //switch to figure out what to display or calculate
        switch(b.getText()){
            case "="://solves equation
            answers.push(solve(text.getText()));//solves equation
            text.setText(String.valueOf(answers.peek()));
            break;
            
            case "Ans": 
            if(!answers.empty()) // Makes sure we don't call nothing to get an error
                text.setText(text.getText()+answers.pop()+" ");
                break;
            case "CE": // Resets current Equation
                text.setText("");
                break;
            case "<-":// deletes last inputted character
                text.setText(text.getText().substring(0,text.getText().length()-1));
                break;
            case "-":// needed in order to make the int negative since without case it would be -_num instead of -num 
                text.setText(text.getText()+"-");break;
             default:
                text.setText(text.getText()+b.getText()+" ");


        }




        return b.getText();
    }

    private static Double solve(String equation) {
        //check if equation is long enough for computation
        if(equation.length()<3)return null;
        Scanner scan= new Scanner(equation); // scans through equation
        Stack<Double> read = new Stack<>(); // stack for Post Fix
        while(scan.hasNext()) // Iterates through equation
        { if(scan.hasNextDouble()){
            read.push(scan.nextDouble());
        }else{
            switch(scan.next()){
                //decides which operation to use on equation
                case "+":
                    if(read.size()>1){
                        read.push(read.pop()+read.pop());
                    }else{
                        return null;
                    }break;
                case "—":
                    if(read.size()>1){
                        read.push(read.pop()-read.pop());
                    }else{
                        return null;
                    }break;
                case "*":
                    if(read.size()>1){
                        read.push(read.pop()*read.pop());
                    }else{
                        return null;
                    }break;
                case "÷":
                    if(read.size()>1){
                        read.push((1/read.pop()) * read.pop() );
                    }else{
                        return null;
                    }break; 
            }



        }


        }


        scan.close();
        //returns a rounded number to 2 decimal places
        return round(read.pop(),2);
    }
    //not my code but rounds double to 2 decimal places
    private static Double round(Double value, int places) {

    long factor = (long) Math.pow(10, places);
    value = value * factor;
    long tmp = Math.round(value);
    return (double) tmp / factor;}
    



}

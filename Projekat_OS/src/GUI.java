
import java.io.*;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GUI extends Application
{
	
	private static String sb;
	private static	TextArea gore=new TextArea();
	private static TextField dole=new TextField();
	private	PipedInputStream inp=new PipedInputStream();
	private PipedOutputStream out=new PipedOutputStream();
	private int len=0;
	
	public static void setSBClear() {
		sb="";
		dole.clear();
	}
	
	public static void setHelp() {
		System.out.println("g");
		String help;
		
		help="DIR \t\t\t Displays a list of files and subdirectories in a directory.\n";
		help+="GOTO \t\t Changes dir.\n";
		help+="MD \t\t\t Make dir.\n";
		help+="DD \t\t\t Delete dir.\n";
		help+="RD \t\t\t Rename dir.\n";
		help+="EXE \t\t\t Exe. \n";
		help+="LSPR \t\t List of processes.\n";
		help+="BRPR \t\t Break process.\n";
		help+="BLPR \t\t Blocks process.\n";
		help+="UBLPR \t\t Unblocks process.\n";
		help+="CLEAR \t\t Clears terminal.\n";
		help+="EXIT \t\t\t Closes program.\n";
		
		sb+=help;
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
		VBox root=new VBox();
		gore=new TextArea();
		dole=new TextField();
		inp.connect(out);
		sb="";
		
		
		root.getChildren().setAll(gore,dole);
		
		
		gore.setMinSize(1000, 550);
		gore.setEditable(false);
	//	gore.setStyle("-fx-text-fill:red;");
	
		
		dole.setMinSize(1000, 50);
		dole.setOnAction(e ->{
			try {
				byte niz[]= dole.getText().getBytes();
				out.write(niz);
				len=niz.length;
				
				ShellCommands.readACommand(inp,len);
				
				sb+=">"+dole.getText()+"\n";
				String st=ShellCommands.vratiKomandu();
				sb+=st;
				
				
		        gore.setText(sb);
		        dole.clear();

			}catch (IOException e1) {

				e1.printStackTrace();
			}
			
	    });
		
		
		dole.setOnKeyPressed((e) -> {
			if (e.getCode().equals(KeyCode.UP)) {
				ShellCommands.getIter();
				String prosli = ShellCommands.prethodni();
				if (!prosli.equals("")) {
					dole.setText(prosli);
				//	consoleIn.positionCaret(consoleIn.getLength());
				}
			}else if (e.getCode().equals(KeyCode.DOWN)) {
					ShellCommands.getIter();
					String sledeci=ShellCommands.sledeci();
					if (!sledeci.equals("")) {
						dole.setText(sledeci);
					}
			}
			
		});
		
		
		
		
		Scene scena=new Scene(root,1000,600);
		primaryStage.setScene(scena);
		primaryStage.show();
		
		
	}
	
	
	
	
    public static void main(String[] args) throws IOException 
    {
    
    	
    	launch(args);
    	
    	
    	
    	
    	
    	
    	
    }

	
}
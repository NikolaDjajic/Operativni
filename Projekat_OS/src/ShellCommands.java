
import java.io.IOException;
import java.io.PipedInputStream;
import java.util.ArrayList;

public class ShellCommands {
	
	private static String st;
	private static String command;
	private static ArrayList<String> allCommands=new ArrayList<String>();
	private static int iter;
	
	
	
	
	public static String vratiKomandu() {
	st="";
	allCommands.add(command);	
	iter=allCommands.size();
	command=command.toLowerCase();
	
	String commands[];
	commands=command.split(" ");
	 
	switch(commands[0]) {
	
	case"dir":											//a show directory
		if(commands.length==1) {
			st="pokazi direktorijum\n";
			ShellExe.showDir();	
		}else
			st=errorWithParameters();
		break;
	
	case"goto":											//b go to (promjeni dir)
		if(commands.length==2) {
			String parameter=commands[1];
			ShellExe.go(parameter);
			
		}else
			st=errorWithParameters();
		break;
		
	case"md":											//c make dir
		if(commands.length==2) {
			String parameter=commands[1];
			ShellExe.md(parameter);
		}else
			st=errorWithParameters();
		
		break;
		
	case"dd":											//c delete dir
		if(commands.length==2) {
			String parameter=commands[1];
			ShellExe.dd(parameter);
		}else
			st=errorWithParameters();
		break;
		
	case"rd":											//c rename dir
		if(commands.length==3) {				
			String parameter1=commands[1];
			String parameter2=commands[2];
			ShellExe.rd(parameter1,parameter2);
		}else
			st=errorWithParameters();
		break;
		
	case "exe":											//d execute 
		if(commands.length==3) {				
			String parameter1=commands[1];
			String parameter2=commands[2];
			ShellExe.exe(parameter1,parameter2);
		}else
			st=errorWithParameters();
		break;
		
	case "lspr":										//e list processes
		if(commands.length==1) {
			ShellExe.lspr();
			st="Lista procesa";
		}else
			st=errorWithParameters();
		break;
		
	case "brpr":										//f break process
		if(commands.length==2) {
			String parameter=commands[1];
			ShellExe.brpr(parameter);
		}else
			st=errorWithParameters();
		
		break;
		
	case "blpr":										//g block process
		if(commands.length==2) {
			String parameter=commands[1];
			st="blokiraj proces";
			ShellExe.blpr(parameter);
		}else
			st=errorWithParameters();
		break;
		
	case "ublpr":										//g ublock process
		if(commands.length==2) {
			String parameter=commands[1];
			st="unblokiraj proces";
			ShellExe.ublpr(parameter);
		}else
			st=errorWithParameters();
		break;
		
	case "clear":										//clear terminal
		ShellExe.clear();
		System.out.println("clear");
		break;
		
	case "exit":										//h exit
		if(commands.length==1) {
			ShellExe.exit();
			
		}else
			st=errorWithParameters();
		break;
		
	case "help":										//help
		if(commands.length==1) {
			ShellExe.help();
		}else
			st=errorWithParameters();
		break;
	
	default:
		st="Nepostojeca komanda\n";		
	}
	return st;
	}
	
	
	public static String prethodni() {
		String rez="";
		if(!allCommands.isEmpty()) {
			if(iter>=0)
			{
				iter--; 
				if(iter<=0)
					iter=0;
				rez=allCommands.get(iter);
			}
		}
		return rez;
	}
	
	public static String sledeci() {
		String rez="";
		if(!allCommands.isEmpty()) 
			if(iter<allCommands.size()-1)
			{
				iter++;
				if(iter>allCommands.size()-1)
					iter=allCommands.size()-1;
				rez=allCommands.get(iter);
				
			}
		return rez;
	}
	
	public static void readACommand(PipedInputStream inp,int len){
		command="";
		char c;
		
			for(int i=0;i<len;i++) {
				try {
					c = (char) inp.read();
					command+=c;
				} catch (IOException e) {
					e.printStackTrace();
					System.out.println("Uso u eror");
				}	
			}	
	}
	
	public static void getIter() {
		System.out.println(iter);
	}
	
	private static String errorWithParameters(){
		String s="Nepravilno uneseni parametri!\n";
		return s;
	}
	
}

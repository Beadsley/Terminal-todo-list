import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.InputMismatchException;

public class Interaction {

    private ToDoList newList;
    private Scanner sc;
    private boolean open;
    private boolean skipping;

    public Interaction(){
        newList=new ToDoList();
        sc= new Scanner(System.in);
        open=true;
    }

    /*
     *  Creates a scanner to read input from the terminal
     */

    public void getInput(){          
        printWelcome();
        while(open) {
        	skipping =false;
        	try {
        		int input=sc.nextInt();

        		switch (input){
        			case 1:	System.out.println("***** Ciao for now *****");
        					open=false;
        					skipping=true;
                			break;
                	case 2:	sc.nextLine();
                			addTask();
                			break;
                	case 3: sc.nextLine();
                			newList.printList();
                			break;
                	case 4: sc.nextLine();
                			findTask();
                			break;
                	case 5: sc.nextLine();
                			getTaskInfo();
                			break;

        		}
        		if(!skipping){
        		System.out.println(">> Anything else i can help you with Madam?");
        		}
        	
    		}
        
        catch (InputMismatchException e) {
    		System.err.println("Input must be a number");
    		sc.nextLine();
    		}
		}            
    }
    
    /*
     * method adds a new task
    */
    private void addTask(){
		System.out.println(">> Enter task e.g clean house");
		String taskName=sc.nextLine().trim();
        	if(taskName.equals("")){
				System.out.println("***** WARNING! No task entered *****");
                System.out.println(">> Type <2> to try again");                 
                }
			else{
            	newList.addToList(taskName);
				System.out.println("----> '"+taskName+"' Added :)");
                }
    }
    /*
    * Method sees if the task exists in the todo list
    //maybe switch this method to the ToDoList class
    */
    private void findTask(){
		System.out.println(">> Enter task to find e.g clean house");            	
		String task2Find=sc.nextLine().trim();
        boolean found=newList.taskExists(task2Find);
        	if(found){
            	System.out.println("----> '"+task2Find+"' Exists :)");
            }
            else{
               	System.out.println("----> '"+task2Find+"' Doesn't exist :(");
            }
	}

    /*
        *  prints out the opening message alongside intial instructions

     */
    private void printWelcome(){
        System.out.println("--------------------------------------------------");
    	System.out.println(">> **** Main Menu ****");
        System.out.println(">> Type <1> to EXIT the scanner");
        System.out.println(">> Type <2> to ADD a task");
        System.out.println(">> Type <3> to PRINT the list");
        System.out.println(">> Type <4> to FIND a task");
        System.out.println(">> Type <5> for TASK INFORMATION");
        System.out.println("--------------------------------------------------");
    }
    /*
    *	method that prints the opening message to the task information
    */

    private void printTaskInfoWelcome(){
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++");    	
    	System.out.println(">> **** Task Information ****");
    	System.out.println(">> Type <999> to exit back to the main menu");
    	System.out.println(">> Enter task number e.g 1");
    	System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++");    	
    }
    /*
    * Method prints out informtaion about a task
    */

    public void getTaskInfo(){
    	printTaskInfoWelcome();
    	newList.printList();        
    	
    	boolean leaveTaskInfo=false;
    	while(!leaveTaskInfo){

   			 try{   		
    		
    			Integer input = sc.nextInt();
     			if(input.equals(999)){			//add if statement to check if index is within the bounds
    				leaveTaskInfo=true;
    				printWelcome();
    			} 
    			else if (input>=newList.getListSize()){
    				System.out.println("****** WARNING! '"+input+"' not in list *******");
    		
    			}
    			else{
    				Task t=newList.getTask(input);
    				System.out.println("--------------------------------------------------");
    				System.out.println(t.getTaskName());
    				System.out.println("--------------------------------------------------");
    				System.out.println("Created:");
    				System.out.println(t.getCreationDate());
    				System.out.println(t.getCreationTime());
    				System.out.println(t.lifeTime());
    				System.out.println("--------------------------------------------------");
    				//retreive information about the first element

    			} 
    		}
        	catch (InputMismatchException e) {
    			System.err.println("Input must be a number");
    			sc.nextLine();
    		}   			  			
    		
    	}
    }


    /*
    * method creates a new list and saves it to a file
    */
    private void newList(){
    	System.out.println(">> Enter file path e.g. /Users/");
        String filePath = sc.nextLine();
        newList.exportFile(filePath);
    }
 }


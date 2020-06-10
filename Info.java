package jubic;

public class Info
{
	
	private String name;
	private String desc;
	private String comm;
	
	public Info(String name, String desc, String comm)
	{
		this.name = name;
		this.desc = desc;
		this.comm = comm;
	}
	
	
	public String divideText(String input)
	{
		//Preparation for the text-dividing script
		String output = "";
		int counter = 0;
		int i;

		for(i=0; i<input.length(); i++) //Adds newlines when the text gets too long for the GUI
		{
			if (counter > 55 && input.charAt(i) == ' ') //Newline is added to replace a space if possible
			{
				output += "\n";
				counter = 0;
			}
			
			else if(counter > 65) //If there are no spaces for 10 characters, the program adds a hyphen and a newline.
			{
				output += input.charAt(i) + "-\n";
				counter = 0;
			}
			
			else
			{
			output += input.charAt(i);
			counter++;
			}
		}
		return output;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return divideText(desc);
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getComm() {
		return divideText(comm);
	}

	public void setComm(String comm) {
		this.comm = comm;
	}

}

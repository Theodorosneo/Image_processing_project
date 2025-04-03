/**
* Author: THEODOROS NEOPHYTOU
* Written: 01/11/2024
* Last updated: 11/11/2024
*
* Compilation: javac ImageProcessing.java
* Execution: java ImageProcessing
*
* Information about what my program does:
* 
* My program takes the pictures that are given to us and converts them from .bmp to table with 3 dimensions (coloured pictures).
* Then we are asked to create some functions so we can solve each task(Pixel RGB values,horizontal flip, vertical flip.right rotate, left rotate and greyscale)
* If the method provided via the Command line arguments is the display method then we only need one image as a .bmp and the height and width value of the element we have to find the RGB values of.
* If the method is one of the other 5 methods then we only need the name of the file/files.(We could have more than one image to convert)
* So, we have a counter variable initialised with the value 1, for each of the filenames provided via the Command line arguments.
* We will use the args.length method so we have the number of the command line arguments.
* Then, we will have a while loop of which the condition will be if the count variable is less than the length of the command line arguments.
* The reason why the count variable is initialised as 1  is because the argument[0] is the method given by the user.
* So the filenames will be given from the argument number 1 until the argument number args.length-1.
* Thus the while loop for each of the files/images we have to convert.
* To check if the filenames are given as .bmp we will have a for loop which will be used for the check of the filenames.
* If one of the filenames are not given as .bmp the boolean variable will be set to false and thus the for loop will break.
* If the boolean variable is true then that means that all the filenames are correctly given and we can continue with the conversion. 
* To check if it is a .bmp file I am using the endsWith function given from the String API explained to us in the lecture.
* Other way to be completed is to get a substring from the filename.length()-4 until filename.length() without getting the filename.length() position.
* 
* Each task is explained down below:
* -Firstly we are working on the Pixel RGB values task:
* For this task we check if the filename is given as bmp and  if the height and width values are correctly given.
* For the check if they are correctly given we have to check if the arguments given via the command line equal to 4.( method,filename,height,length)
* If it doesn't equal to 4 then there is something missing from the command line arguments and the program prints an error message and  is terminated.
* The provided code gives us the image in the form of a 3 dimension table ( 1st dimension the height of the table the 2nd dimension is the width of the table and the 3rd dimension is the RGB values).
* Then the user gives the coordinations of the height and the width from the command line argument , and the program provides them with the RGB values at the certain height and width.
* That is achieved by setting the first dimension of the table to x (height value) and the second dimension to y (the width value) and then printing the value of the R the G and the B (each value
* in the third dimension).
* We will create a function called display for the certain task.
* -Second task - horizontal flip:
* We are working with the table of the image the user provides via the command line argument (Given code).
* To achieve the horizontal flip we only need to flip each pixel of the second dimension of the table (lines).
* In order to do that we set up a for loop from the pixel 0 of each line to the pixel N/2 which N equals to the pixels of each line and we reverse the pixel i with the pixel N-i-1.(temp variable needed)
* For the task we will create a function called hflip.
* -Third task- vertical flip:
* Basically we will use the same procedure used for the horizontal flip but now we will flip the pixel of each column and not each line.(1st dimension of the table)
* For the task we will create a function called vflip.
* -Fourth task- Right rotation:
* For the certain task we will again use the provided code that turns an image to a 3 dimension table.
* Then to rotate right our picture we will create a new 3 dimension table to add the rotated pixels inside.
* To rotate the elements in the table , the element in the [i][j][k] position of the initial table( table image[][][]) goes in the position [j][height -i-1][k] of the new table.
* For example the element in the position [0][0][k] will go to the position [0][N-1][k] if the height equals to N.
* Thus, creating the rotation effect.
* For the task we will create a function called right90.
* -Fifth task- left rotation:
* Basically the procedure is the same but now the only difference we have is that the element in the position [i][j][k] of the initial table (table image[][][]) goes in the position [width -j-1][i][k].
* For example the element in position [3][2][k] will go to the position [N-3][3][k] of the new table if the width equals to N.
* For the task we will create a function called left90.
* -Sixth task- Grayscale:
* For this task we have to convert each coloured pixel to a black and white one.
* To achieve that we have to use the luminance equation (0.299*red + 0.587*green + 0.114*blue).
* Then the result of the equation will be saved to each pixel of the 2 dimension table called greyimage.
* Lastly,the outcome of the function gray will be the image provided by the user in the Command line Argument but black and white.
* The image will be saved to a file called grey-(...).bmp using the function saveGrayscaleBMP.
* 
*/
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageProcessing{
	public static int [][][] loadBMP(String filename) {
		BufferedImage img = null;
		try {
		img = ImageIO.read(new File(filename));
		} catch (IOException e) {
		System.out.println(e);
		}
		int height = img.getHeight();
		int width = img.getWidth();
		int image[][][] = new int[height][width][3];
		int rgb;
		for (int h=0; h<height; h++)
		{
		for (int w = 0; w<width; w++)
		{
		rgb = img.getRGB(w, h);
		image[h][w][0] = (rgb >> 16 ) & 0x000000FF;
		image[h][w][1] = (rgb >> 8 ) & 0x000000FF;
		image[h][w][2] = (rgb) & 0x000000FF;
		}
		}
		return image;
		}
		public static void saveBMP(int image[][][], String filename) {
		int height = image.length;
		int width = image[0].length;
		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		int p, r, g, b;
		for (int h=0; h<height; h++)
		{
		for (int w = 0; w<width; w++)
		{
		r = image[h][w][0];
		g = image[h][w][1];
		b = image[h][w][2];
		// set the pixel value
		p = (r << 16) | (g << 8) | b;
		img.setRGB(w, h, p);
		}}
		
		try {
			File f = new File(filename);
			ImageIO.write(img, "bmp", f);
			}
			catch (IOException e) {
			System.out.println(e);
			}
			}
			public static void saveGrayscaleBMP(int image[][], String filename) {
			int height = image.length;
			int width = image[0].length;
			BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			for (int h=0; h<height; h++)
			{
			for (int w = 0; w<width; w++)
			{
			int p = (image[h][w] << 16) | (image[h][w] << 8) | image[h][w];
			img.setRGB(w, h, p);
			}
			}
			// write image
			try {
			File f = new File(filename);
			ImageIO.write(img, "bmp", f);
			}
			catch (IOException e) {
			System.out.println(e);
			}
			}
			
	public static void display(int image[][][],int x,int y) {
		System.out.println("RGB["+x+","+y+"]= ("+image[x][y][0]+","+image[x][y][1]+","+image[x][y][2]+")");
	}
	public static void hflip(int image[][][], String filename) {
		for (int i=0;i<image.length;i++)
			for(int j=0;j<image[i].length/2;j++) {
				int temp1=image[i][j][0];
				image[i][j][0]=image[i][image[i].length-j-1][0];
				image[i][image[i].length-j-1][0]=temp1;
				int temp2=image[i][j][1];
				image[i][j][1]=image[i][image[i].length-j-1][1];
				image[i][image[i].length-j-1][1]=temp2;
				int temp3=image[i][j][2];
				image[i][j][2]=image[i][image[i].length-j-1][2];
				image[i][image[i].length-j-1][2]=temp3;
			}
		saveBMP(image,"hflip-"+filename);
	 }

	public static void vflip(int image[][][], String filename) {
		int height=image.length;
		int width=image[0].length;
		for (int j=0;j<width;j++)
			for(int i=0;i<height/2;i++) {
				int temp1=image[i][j][0];
				image[i][j][0]=image[height-i-1][j][0];
				image[height-i-1][j][0]=temp1;
				int temp2=image[i][j][1];
				image[i][j][1]=image[height-i-1][j][1];
				image[height-i-1][j][1]=temp2;
				int temp3=image[i][j][2];
				image[i][j][2]=image[height-i-1][j][2];
				image[height-i-1][j][2]=temp3;
			}
		saveBMP(image,"vflip-"+filename);
	 }

	public static void right90(int image[][][],String filename) {		
		int height=image.length;
		int width=image[0].length;
		int right90image[][][]=new int[width][height][3];
		for(int i=0;i<height;i++)
			for(int j=0;j<width;j++)
				for(int k=0;k<3;k++)
					right90image[j][height-i-1][k]=image[i][j][k];
		saveBMP(right90image,"right90-"+filename);
	}

	public static void left90(int image[][][],String filename) {
		int height=image.length;
		int width=image[0].length;
		int left90image[][][]=new int[width][height][3];
		for(int i=0;i<height;i++)
			for(int j=0;j<width;j++)
				for(int k=0;k<3;k++)
					left90image[width-j-1][i][k]=image[i][j][k];
		saveBMP(left90image,"left90-"+filename);
	}
	public static void grey(int image[][][],String filename) {
	int height=image.length;
	int width=image[0].length;
	int greyimage[][]=new int [height][width];
	for (int i=0;i<height;i++)
		for(int j=0;j<width;j++)
			greyimage[i][j]=(int)(Math.round(0.299*image[i][j][0] + 0.587*image[i][j][1]+ 0.114*image[i][j][2]));
	saveGrayscaleBMP(greyimage,"grey-"+filename);
	}
	public static void main(String[] args) {
		String method=args[0];
		if( !method.equals("-hflip") && !method.equals("-vflip") && !method.equals("-right90") && !method.equals("-left90") && !method.equals("-grey")  && !method.equals("-display")) {
			System.out.println("Wrong option given. Program is terminated");
			return;
		}
		else if(method.equals("-display")) {
            if(args.length<4) { 
            	System.out.println("Missing arguments about pixel position. Program is terminated.");
            	return;
            }
            else {
            	String filename=args[1];
            	int image[][][]=loadBMP(filename);
            	int height=Integer.parseInt(args[2]);
            	int width=Integer.parseInt(args[3]);
                if (!filename.endsWith(".bmp")) {
            		System.out.println("Image is not bmp. Program is terminated");
            		return;
            	}
            	else if(height<0 || height>=image.length)
            		System.out.println("Height out of bounds!");
            	else if (width<0 || width>=image[0].length)
            		System.out.println("Width out of bounds");
            	else 
            		display(image,height,width);
            }
		}
		else if(method.equals("-hflip") ||method.equals("-vflip") || method.equals("-right90") || method.equals("-left90")||method.equals("-grey")) {
			boolean bmp=true;
			for( int i=1;i<args.length && bmp;i++) {
				String filename=args[i];
				if (!filename.endsWith(".bmp")){  
		               System.out.println("Image is not bmp. Program is terminated");
		               bmp=false;
				}
			}
			if(bmp) {
			  int count=1;
			  while(count<args.length) {
				String filename=args[count++];
				int image[][][]=loadBMP(filename);
			    if(method.equals("-hflip"))
				  hflip(image,filename);
			    else if (method.equals("-vflip"))
				  vflip(image, filename);
			    else if(method.equals("-right90"))
				  right90(image,filename);
			    else if(method.equals("-left90"))
				  left90(image,filename);
			    else if (method.equals("-grey"))
			      grey(image,filename);
			}
		  }
	   }
	}
}




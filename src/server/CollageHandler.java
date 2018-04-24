package server;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

import javax.imageio.ImageIO;

public class CollageHandler {

 private String topic;
 private boolean borders;
 private boolean rotations;
 private String filter;
 private String letters;
 private Collage newCollage;
 private ImageTransform imageTransformer;

 //initializes the CollageHandler with String parameter t for collage topic
 public CollageHandler(String t, boolean borders, boolean rotations, String filter, String letters) {
  topic = t;
  this.borders = borders;
  this.rotations = rotations;
  this.filter = filter;
  this.letters = letters;
 }

 //builds collage with ImageTransform object and returns Collage object
 public Collage build() {
  imageTransformer = new ImageTransform(topic, borders, rotations, filter, letters);
  BufferedImage completeCollage = imageTransformer.createCollageImage();
  Collage collageWrapper = new Collage();
  collageWrapper.setTopic(topic);
  collageWrapper.setImage(convertBufferedImageToBase64(completeCollage));
  String borderText = "bordersOff";
  if (borders == true) {
   borderText = "bordersOn";
  }
  String rotationText = "rotationsOff";
  if (rotations == true) {
   rotationText = "rotationsOn";
  }
  collageWrapper.aText = this.topic + "-" + this.letters + "-" + this.filter + "-" + borderText + "-" + rotationText;
  return collageWrapper;
 }

 //converts BufferedImage paramater image into returned base64 string
 public String convertBufferedImageToBase64(BufferedImage image) {

  String type = "png";
  String imageString = null;
  ByteArrayOutputStream bos = new ByteArrayOutputStream();
  try {
   // writes bufferedImage to a string
   ImageIO.write(image, type, Base64.getEncoder().wrap(bos));
   imageString = bos.toString();

   bos.close();
  } catch (IOException e) {
   System.out.println("IOException while encoding collage image as a string");
  }
  return imageString;
 }

}
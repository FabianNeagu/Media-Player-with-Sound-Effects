package application;

import java.io.File;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioEqualizer;
import javafx.scene.media.EqualizerBand;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.util.Duration;

public class VideoPlayerController implements Initializable
{
	private String path;
	private Media media;
	private MediaPlayer mediaPlayer;
	
	@FXML 
	private MediaView mediaView;
	@FXML
	private Slider progressBar;
	@FXML
	private Slider volumeSlider;
	@FXML
	private Slider s1,s2,s3,s4,s5,s6,s7,s8,s9,s10;
	@FXML
	private Label l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,vl;
	@FXML
	private Label l11,l22,l33,l44,l55,l66,l77,l88,l99,l100;
	@FXML
	private MenuButton menu=new MenuButton("Effects");
	@FXML
	private MenuItem rock;
	@FXML
	private MenuItem jazz;
	@FXML
	private MenuItem pop;
	@FXML
	private MenuItem dance;
	@FXML
	private MenuItem voice;
	
	private static AudioEqualizer eq;
    private static ObservableList<EqualizerBand> bands;
	
	public void chooseFileMethod(ActionEvent event) 
	{
		FileChooser fileChooser = new FileChooser();
		File file= fileChooser.showOpenDialog(null);
		path=file.toURI().toString();
		System.out.println(path);
		if(path!=null)
		{
			media=new Media(path);
			mediaPlayer=new MediaPlayer(media); 
			mediaPlayer.setAutoPlay(true);
			//mediaView=new MediaView(mediaPlayer);
			mediaView.setMediaPlayer(mediaPlayer);
			
		/*	DoubleProperty widthProp= mediaView.fitWidthProperty();
			DoubleProperty heightProp= mediaView.fitHeightProperty();
			
			widthProp.bind(Bindings.selectDouble(mediaView.sceneProperty(),"width"));
			heightProp.bind(Bindings.selectDouble(mediaView.sceneProperty(),"height"));
			*/
			mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>(){
				@Override
				public void changed(ObservableValue<? extends Duration> observable,Duration oldValue,Duration newValue) {
					progressBar.setValue(newValue.toSeconds());
				}
			});
			
			progressBar.setOnMousePressed(new EventHandler<MouseEvent>() 
			{
				public void handle(MouseEvent event)
				{
					mediaPlayer.seek(Duration.seconds(progressBar.getValue()));
				}
			});
			
			progressBar.setOnMouseDragged(new EventHandler<MouseEvent>() 
			{
				public void handle(MouseEvent event)
				{
					mediaPlayer.seek(Duration.seconds(progressBar.getValue()));
				}
			});
			
			mediaPlayer.setOnReady(new Runnable() {
				@Override
				public void run()
				{
					Duration total=media.getDuration();
					progressBar.setMax(total.toSeconds());
					l11.setText("32");
					
				}
			});
			
			volumeSlider.setValue(mediaPlayer.getVolume()*100);
			volumeSlider.valueProperty().addListener(new InvalidationListener() {
				@Override
				public void invalidated(Observable observable)
				{
					mediaPlayer.setVolume(volumeSlider.getValue()/100);
					vl.setText(new DecimalFormat("###").format(volumeSlider.getValue()));
					
				}
			});
			eq = mediaPlayer.getAudioEqualizer();
	        bands = eq.getBands();
			 //125Hz
	        s3.setValue(bands.get(2).getGain());
	        s3.valueProperty().addListener(new InvalidationListener() {
				@Override
				public void invalidated(Observable observable)
				{
					bands.get(2).setGain(s3.getValue());
					l3.setText(new DecimalFormat("##.#").format(bands.get(2).getGain()));
				}
			});
	        
	        //250Hz
	        s4.setValue(bands.get(3).getGain());
	        s4.valueProperty().addListener(new InvalidationListener() {
				@Override
				public void invalidated(Observable observable)
				{
					bands.get(3).setGain(s4.getValue());
					l4.setText(new DecimalFormat("##.#").format(bands.get(3).getGain()));
				}
			});
	        //500Hz
	        s5.setValue(bands.get(4).getGain());
	        s5.valueProperty().addListener(new InvalidationListener() {
				@Override
				public void invalidated(Observable observable)
				{
					bands.get(4).setGain(s5.getValue());
					l5.setText(new DecimalFormat("##.#").format(bands.get(4).getGain()));
				}
			});
	        //1Khz
	        s6.setValue(bands.get(5).getGain());
	        s6.valueProperty().addListener(new InvalidationListener() {
				@Override
				public void invalidated(Observable observable)
				{
					bands.get(5).setGain(s6.getValue());
					l6.setText(new DecimalFormat("##.#").format(bands.get(5).getGain()));
				}
			});
	        //2Khz
	        s7.setValue(bands.get(6).getGain());
	        s7.valueProperty().addListener(new InvalidationListener() {
				@Override
				public void invalidated(Observable observable)
				{
					bands.get(6).setGain(s7.getValue());
					l7.setText(new DecimalFormat("##.#").format(bands.get(6).getGain()));
				}
			});
	        //4Khz
	        s8.setValue(bands.get(7).getGain());
	        s8.valueProperty().addListener(new InvalidationListener() {
				@Override
				public void invalidated(Observable observable)
				{
					bands.get(7).setGain(s8.getValue());
					l8.setText(new DecimalFormat("##.#").format(bands.get(7).getGain()));
				}
			});
	        //8Khz
	        s9.setValue(bands.get(8).getGain());
	        s9.valueProperty().addListener(new InvalidationListener() {
				@Override
				public void invalidated(Observable observable)
				{
					bands.get(8).setGain(s9.getValue());
					l9.setText(new DecimalFormat("##.#").format(bands.get(8).getGain()));
				}
			});
	        //16Khz
	        s10.setValue(bands.get(9).getGain());
	        s10.valueProperty().addListener(new InvalidationListener() {
				@Override
				public void invalidated(Observable observable)
				{
					bands.get(9).setGain(s10.getValue());
					l10.setText(new DecimalFormat("##.#").format(bands.get(9).getGain()));
				}
			});
	        //nb : freq9 & 10 sont les bandes 32 & 64 Hz
	        s1.setValue(bands.get(0).getGain());
	        s1.valueProperty().addListener(new InvalidationListener() {
				@Override
				public void invalidated(Observable observable)
				{
					bands.get(0).setGain(s1.getValue());
					l1.setText(new DecimalFormat("##.#").format(bands.get(0).getGain()));
				}
			});
	        s2.setValue(bands.get(1).getGain());
	        s2.valueProperty().addListener(new InvalidationListener() {
				@Override
				public void invalidated(Observable observable)
				{
					bands.get(1).setGain(s2.getValue());
					l2.setText(new DecimalFormat("##.#").format(bands.get(1).getGain()));
				}
			});
		
			mediaPlayer.play();
	        eq.setEnabled(true);
	      
		}
	}
		
	public void playMethod(ActionEvent event) 
	{
		mediaPlayer.play();
		mediaPlayer.setRate(1);
	}
	public void pauseMethod(ActionEvent event) 
	{
		mediaPlayer.pause();
	}
	public void stopMethod(ActionEvent event) 
	{
		mediaPlayer.stop();
		System.out.println(bands.size());
	}
	public void slowRateMethod(ActionEvent event)
	{
		mediaPlayer.setRate(mediaPlayer.getRate()/2);
	}
	public void fastRateMethod(ActionEvent event)
	{
		mediaPlayer.setRate(mediaPlayer.getRate()*2);
	}
	public void skip(ActionEvent event)
	{
		mediaPlayer.seek(mediaPlayer.getCurrentTime().add(Duration.seconds(10)));
	}
	public void back(ActionEvent event)
	{
		mediaPlayer.seek(mediaPlayer.getCurrentTime().add(Duration.seconds(-10)));
	}
	public void setSliders()
	{
		s1.setValue(bands.get(0).getGain());
		s2.setValue(bands.get(1).getGain());
		s3.setValue(bands.get(2).getGain());
		s4.setValue(bands.get(3).getGain());
		s5.setValue(bands.get(4).getGain());
		s6.setValue(bands.get(5).getGain());
		s7.setValue(bands.get(6).getGain());
		s8.setValue(bands.get(7).getGain());
		s9.setValue(bands.get(8).getGain());
		s10.setValue(bands.get(9).getGain());
		
	}
	@FXML
	private void rock(ActionEvent event)
	{
		bands.get(0).setGain(3.7);
		bands.get(1).setGain(2.4);
		bands.get(2).setGain(2.1);
		bands.get(3).setGain(0.7);
		bands.get(4).setGain(-0.3);
		bands.get(5).setGain(-0.5);
		bands.get(6).setGain(0.4);
		bands.get(7).setGain(0.8);
		bands.get(8).setGain(1.2);
		bands.get(9).setGain(1.4);
		setSliders();
	}
	@FXML
	private void pop(ActionEvent event)
	{
		bands.get(0).setGain(-0.9);
		bands.get(1).setGain(-0.7);
		bands.get(2).setGain(0.5);
		bands.get(3).setGain(1);
		bands.get(4).setGain(2.9);
		bands.get(5).setGain(2.5);
		bands.get(6).setGain(1.6);
		bands.get(7).setGain(0.6);
		bands.get(8).setGain(-0.4);
		bands.get(9).setGain(-0.6);
		setSliders();
	}
	@FXML
	private void jazz(ActionEvent event) //Bass
	{
		bands.get(0).setGain(3.9);
		bands.get(1).setGain(2.5);
		bands.get(2).setGain(2.1);
		bands.get(3).setGain(1.9);
		bands.get(4).setGain(0.9);
		bands.get(5).setGain(0.4);
		bands.get(6).setGain(0.2);
		bands.get(7).setGain(0.1);
		bands.get(8).setGain(0.1);
		bands.get(9).setGain(0.1);
		setSliders();
	}
	@FXML
	private void voice(ActionEvent event)//accoustic
	{
		bands.get(0).setGain(3.7);
		bands.get(1).setGain(3.5);
		bands.get(2).setGain(3.2);
		bands.get(3).setGain(0.4);
		bands.get(4).setGain(1.4);
		bands.get(5).setGain(1.2);
		bands.get(6).setGain(2.2);
		bands.get(7).setGain(2.4);
		bands.get(8).setGain(1.2);
		bands.get(9).setGain(1.8);
		setSliders();
	}
	@FXML
	private void dance(ActionEvent event)
	{
		bands.get(0).setGain(2.4);
		bands.get(1).setGain(2);
		bands.get(2).setGain(0.3);
		bands.get(3).setGain(0.2);
		bands.get(4).setGain(-0.5);
		bands.get(5).setGain(-0.4);
		bands.get(6).setGain(-0.3);
		bands.get(7).setGain(0);
		bands.get(8).setGain(1.9);
		bands.get(9).setGain(2.3);
		setSliders();
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		
		rock.setStyle("-fx-padding: 0.0em 5.1em 0.0em 0.0em; -fx-font: normal bold 20px 'serif' ");
		dance.setStyle("-fx-font: normal bold 20px 'serif' ");
		voice.setStyle("-fx-font: normal bold 20px 'serif' ");
		pop.setStyle("-fx-font: normal bold 20px 'serif' ");
		jazz.setStyle("-fx-font: normal bold 20px 'serif' ");
		
	}

}

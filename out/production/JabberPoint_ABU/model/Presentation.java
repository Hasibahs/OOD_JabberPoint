package model;

import view.SlideViewer;

import java.util.ArrayList;
import java.util.List;

public class Presentation {

	private String title;
	private final List<Slide> slides;
	private int currentSlideIndex = 0;
	private SlideViewer slideViewerComponent;

	public Presentation() {
		this(null);
	}

	public Presentation(SlideViewer slideViewerComponent) {
		this.slideViewerComponent = slideViewerComponent;
		slides = new ArrayList<>();
		title = "";
	}

	public int getNumberOfSlides() {
		return slides.size();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String newTitle) {
		title = newTitle;
	}

	public void setSlideViewerComponent(SlideViewer newSlideViewerComponent) {
		this.slideViewerComponent = newSlideViewerComponent;
	}

	public int getCurrentSlideIndex() {
		return currentSlideIndex;
	}

	public void setCurrentSlideIndex(int newSlideIndex) {
		if (isValidSlideIndex(newSlideIndex)) {
			currentSlideIndex = newSlideIndex;
			updateSlideViewerComponent();
		}
	}

	public void previousSlide() {
		setCurrentSlideIndex(currentSlideIndex - 1);
	}

	public void nextSlide() {
		setCurrentSlideIndex(currentSlideIndex + 1);
	}

	public void appendSlide(Slide slide) {
		slides.add(slide);
	}

	public Slide getSlide(int index) {
		if (isValidSlideIndex(index)) {
			return slides.get(index);
		}
		return null;
	}

	public Slide getCurrentSlide() {
		return getSlide(currentSlideIndex);
	}

	public List<Slide> getSlides() {
		return new ArrayList<>(slides); // Return a copy of the slides list to prevent external modification
	}


	public void clear() {
		slides.clear();
		currentSlideIndex = 0;
	}

	protected void updateSlideViewerComponent() {
		if (slideViewerComponent != null && isValidSlideIndex(currentSlideIndex)) {
			slideViewerComponent.update(this, getCurrentSlide());
		}
	}

	private boolean isValidSlideIndex(int index) {
		return index >= 0 && index < getNumberOfSlides();
	}
}

package model;

import view.SlideViewer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Presentation {

	private final List<Slide> slides = new ArrayList<>();
	private SlideViewer slideViewer; // Renamed for clarity:
	private String title = "";
	private int currentSlideIndex = 0;

	public Presentation(SlideViewer slideViewer) {
		this.slideViewer = slideViewer;
	}

	public Presentation() {
		this(null);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String newTitle) {
		title = newTitle;
	}

	public int getCurrentSlideIndex() {
		return currentSlideIndex;
	}

	public void setCurrentSlideIndex(int newSlideIndex) {
		if (isValidSlideIndex(newSlideIndex)) {
			currentSlideIndex = newSlideIndex;
			updateSlideViewer();
		}
	}

	public void nextSlide() {
		setCurrentSlideIndex(currentSlideIndex + 1);
	}

	public void previousSlide() {
		setCurrentSlideIndex(currentSlideIndex - 1);
	}

	public int getNumberOfSlides() {
		return slides.size();
	}

	public Optional<Slide> getSlide(int index) {
		return isValidSlideIndex(index) ? Optional.of(slides.get(index)) : Optional.empty();
	}

	public Optional<Slide> getCurrentSlide() {
		return getSlide(currentSlideIndex);
	}

	public List<Slide> getSlides() {
		return Collections.unmodifiableList(slides);
	}

	public void appendSlide(Slide slide) {
		slides.add(slide);
	}

	public void clear() {
		slides.clear();
		currentSlideIndex = 0;
	}

	public void setSlideViewer(SlideViewer slideViewer) {
		this.slideViewer = slideViewer;
	}

	// Updated the method name for clarity
	private void updateSlideViewer() {
		getCurrentSlide().ifPresent(slide -> {
			if (slideViewer != null) {
				slideViewer.update(this, slide);
			}
		});
	}

	private boolean isValidSlideIndex(int index) {
		return index >= 0 && index < getNumberOfSlides();
	}
}

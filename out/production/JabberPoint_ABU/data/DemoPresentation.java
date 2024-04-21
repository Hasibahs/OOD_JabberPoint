package data;

import model.Presentation;
import model.Slide;
import model.slideitems.BitmapItem;
import model.slideitems.TextItem;

/**
 * A class responsible for loading a demo presentation.
 */
public class DemoPresentation implements PresentationLoader {

	private static final String PRESENTATION_TITLE = "Demo Presentation";
	private static final String IMAGE_PATH = "JabberPoint.jpg";

	@Override
	public void load(Presentation presentation, String unusedFilename) {
		presentation.setTitle(PRESENTATION_TITLE);
		presentation.appendSlide(createIntroductionSlide());
		presentation.appendSlide(createLevelsSlide());
		presentation.appendSlide(createClosingSlide());
	}

	private Slide createIntroductionSlide() {
		Slide slide = new Slide();
		slide.setTitle("JabberPoint");
		slide.addSlideItem(new TextItem(1, "The Java presentation tool"));
		slide.addSlideItem(new TextItem(2, "How to navigate the presentation:"));
		slide.addSlideItem(new TextItem(3, "Next slide: PgDn or Enter"));
		slide.addSlideItem(new TextItem(3, "Previous slide: PgUp or up-arrow"));
		slide.addSlideItem(new TextItem(3, "Quit: q or Q"));
		return slide;
	}

	private Slide createLevelsSlide() {
		Slide slide = new Slide();
		slide.setTitle("Demonstration of levels and styles");
		slide.addSlideItem(new TextItem(1, "Level 1"));
		slide.addSlideItem(new TextItem(2, "Level 2"));
		slide.addSlideItem(new TextItem(1, "Again level 1"));
		slide.addSlideItem(new TextItem(1, "Level 1 has style number 1"));
		slide.addSlideItem(new TextItem(2, "Level 2 has style number 2"));
		slide.addSlideItem(new TextItem(3, "This is how level 3 looks like"));
		slide.addSlideItem(new TextItem(4, "And this is level 4"));
		return slide;
	}

	private Slide createClosingSlide() {
		Slide slide = new Slide();
		slide.setTitle("The third slide");
		slide.addSlideItem(new TextItem(1, "This concludes the Demo Presentation."));
		slide.addSlideItem(new BitmapItem(1, IMAGE_PATH));
		return slide;
	}
}

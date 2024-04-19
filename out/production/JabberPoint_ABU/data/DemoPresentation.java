package data;

import model.Presentation;
import model.Slide;
import model.slideitems.BitmapItem;
import model.slideitems.TextItem;


public class DemoPresentation extends Accessor {

	private static final String PRESENTATION_TITLE = "Demo Presentation";
	private static final String SLIDE_TITLE_JABBERPOINT = "JabberPoint";
	private static final String SLIDE_TITLE_DEMO_LEVELS = "Demonstration of levels and styles";
	private static final String SLIDE_TITLE_THIRD_SLIDE = "The third slide";

	private static final String IMAGE_PATH = "JabberPoint.jpg";


	@Override
	public void loadFile(Presentation presentation, String unusedFilename) {
		presentation.setTitle(PRESENTATION_TITLE);

		presentation.appendSlide(createIntroductionSlide());
		presentation.appendSlide(createLevelsSlide());
		presentation.appendSlide(createClosingSlide());
	}


	@Override
	public void saveFile(Presentation presentation, String unusedFilename) {
		throw new UnsupportedOperationException("Saving is not supported for the demo presentation.");
	}

	private Slide createIntroductionSlide() {
		Slide slide = new Slide();
		slide.setTitle(SLIDE_TITLE_JABBERPOINT);
		slide.addSlideItem(new TextItem(1, "The Java presentation tool"));
		slide.addSlideItem(new TextItem(2, "Copyright (c) 1996-2000: Ian Darwin"));
		slide.addSlideItem(new TextItem(2, "Copyright (c) 2000-now: Gert Florijn and Sylvia Stuurman"));
		slide.addSlideItem(new TextItem(4, "Calling Jabberpoint without a filename will show this presentation"));
		slide.addSlideItem(new TextItem(1, "Navigate:"));
		slide.addSlideItem(new TextItem(3, "Next slide: PgDn or Enter"));
		slide.addSlideItem(new TextItem(3, "Previous slide: PgUp or up-arrow"));
		slide.addSlideItem(new TextItem(3, "Quit: q or Q"));
		return slide;
	}


	private Slide createLevelsSlide() {
		Slide slide = new Slide();
		slide.setTitle(SLIDE_TITLE_DEMO_LEVELS);
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
		slide.setTitle(SLIDE_TITLE_THIRD_SLIDE);
		slide.addSlideItem(new TextItem(1, "To open a new presentation, use File->Open from the menu."));
		slide.addSlideItem(new TextItem(1, " "));
		slide.addSlideItem(new TextItem(1, "This is the end of the presentation."));
		slide.addSlideItem(new BitmapItem(1, IMAGE_PATH));
		return slide;
	}
}

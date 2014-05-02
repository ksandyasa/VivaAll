package id.co.viva.pialadunia.object;

public class FrontHeadline {
	private String id, title, path_thumbnail;
	
	public FrontHeadline(String id, String title, String path_thumbnail) {
		this.setId(id);
		this.setTitle(title);
		this.setPathThumbnail(path_thumbnail);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPathThumbnail() {
		return path_thumbnail;
	}

	public void setPathThumbnail(String path_thumbnail) {
		this.path_thumbnail = path_thumbnail;
	}
}
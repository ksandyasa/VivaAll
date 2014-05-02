package id.co.viva.pialadunia.object;

public class News {
	private String id, title, date_publish, path_thumbnail;
	
	public News(String id, String title, String date_publish, String path_thumbnail) {
		this.setId(id);
		this.setTitle(title);
		this.setDatePublish(date_publish);
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

	public String getDatePublish() {
		return date_publish;
	}

	public void setDatePublish(String date_publish) {
		this.date_publish = date_publish;
	}

	public String getPathThumbnail() {
		return path_thumbnail;
	}

	public void setPathThumbnail(String path_thumbnail) {
		this.path_thumbnail = path_thumbnail;
	}
}
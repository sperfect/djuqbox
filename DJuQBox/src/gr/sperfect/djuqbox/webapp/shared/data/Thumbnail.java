package gr.sperfect.djuqbox.webapp.shared.data;

public class Thumbnail extends BaseDataClass {

	private Long width;
	private Long height;
	private String url;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Long getHeight() {
		return height;
	}
	public void setHeight(Long height) {
		this.height = height;
	}
	public Long getWidth() {
		return width;
	}
	public void setWidth(Long width) {
		this.width = width;
	}

}

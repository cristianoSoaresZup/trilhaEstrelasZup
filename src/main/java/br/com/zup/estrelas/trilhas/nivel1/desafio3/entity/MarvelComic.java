package br.com.zup.estrelas.trilhas.nivel1.desafio3.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class MarvelComic {
	
	@Column(nullable = false, unique = true)
	@Id
	private Long id;
	private String title;
	private String variantDescription;
	private String upc;
	private String ean;
	private String format;
	private String pageCount;
	private String resourceURI;
	private List<Serie> series;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ean == null) ? 0 : ean.hashCode());
		result = prime * result + ((format == null) ? 0 : format.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((pageCount == null) ? 0 : pageCount.hashCode());
		result = prime * result + ((resourceURI == null) ? 0 : resourceURI.hashCode());
		result = prime * result + ((series == null) ? 0 : series.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((upc == null) ? 0 : upc.hashCode());
		result = prime * result + ((variantDescription == null) ? 0 : variantDescription.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MarvelComic other = (MarvelComic) obj;
		if (ean == null) {
			if (other.ean != null)
				return false;
		} else if (!ean.equals(other.ean))
			return false;
		if (format == null) {
			if (other.format != null)
				return false;
		} else if (!format.equals(other.format))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (pageCount == null) {
			if (other.pageCount != null)
				return false;
		} else if (!pageCount.equals(other.pageCount))
			return false;
		if (resourceURI == null) {
			if (other.resourceURI != null)
				return false;
		} else if (!resourceURI.equals(other.resourceURI))
			return false;
		if (series == null) {
			if (other.series != null)
				return false;
		} else if (!series.equals(other.series))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (upc == null) {
			if (other.upc != null)
				return false;
		} else if (!upc.equals(other.upc))
			return false;
		if (variantDescription == null) {
			if (other.variantDescription != null)
				return false;
		} else if (!variantDescription.equals(other.variantDescription))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "MarvelComic [id=" + id + ", title=" + title + ", variantDescription=" + variantDescription + ", upc="
				+ upc + ", ean=" + ean + ", format=" + format + ", pageCount=" + pageCount + ", resourceURI="
				+ resourceURI + ", series=" + series + "]";
	}

}

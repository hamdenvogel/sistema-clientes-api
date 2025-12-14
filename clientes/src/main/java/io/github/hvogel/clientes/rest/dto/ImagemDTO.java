package io.github.hvogel.clientes.rest.dto;

import java.util.Objects;

import io.github.hvogel.clientes.model.entity.Documento;
import io.github.hvogel.clientes.model.entity.Imagem;


public class ImagemDTO {
	private String uuid;
	private String fileName;
	private String fileType;
	private long size;
	private Integer chaveId;
	private Documento documentoId;
	private String originalFileName;

	public ImagemDTO(Imagem imagem) {
		setUuid(imagem.getUuid());
		setFileName(imagem.getFileName());
		setFileType(imagem.getFileType());
		setSize(imagem.getSize());
		setChaveId(imagem.getChaveId());
		setDocumentoId(documentoId);
		setOriginalFileName(originalFileName);
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public Integer getChaveId() {
		return chaveId;
	}

	public void setChaveId(Integer chaveId) {
		this.chaveId = chaveId;
	}

	public Documento getDocumentoId() {
		return documentoId;
	}

	public void setDocumentoId(Documento documentoId) {
		this.documentoId = documentoId;
	}

	public String getOriginalFileName() {
		return originalFileName;
	}

	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}

	@Override
	public int hashCode() {
		return Objects.hash(chaveId, documentoId, fileName, fileType, originalFileName, size, uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ImagemDTO other = (ImagemDTO) obj;
		return Objects.equals(chaveId, other.chaveId) && Objects.equals(documentoId, other.documentoId)
				&& Objects.equals(fileName, other.fileName) && Objects.equals(fileType, other.fileType)
				&& Objects.equals(originalFileName, other.originalFileName) && size == other.size
				&& Objects.equals(uuid, other.uuid);
	}

	@Override
	public String toString() {
		return "ImagemDTO [uuid=" + uuid + ", fileName=" + fileName + ", fileType=" + fileType + ", size=" + size
				+ ", chaveId=" + chaveId + ", documentoId=" + documentoId + ", originalFileName=" + originalFileName
				+ "]";
	}
	
}

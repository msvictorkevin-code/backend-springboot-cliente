package com.bolsaideas.springboot.backend.apirest.auth;

public class JwtConfig {
	public static final String LLAVE_SECRETA = "alguna.clave.secreta.123456789";
	public static final String RSA_PRIVADA = "-----BEGIN RSA PRIVATE KEY-----\r\n" + 
			"MIIEogIBAAKCAQEAmxrkHlq9KdCp00nqWTWTazkvEwl5YJ0BxdsTuiiXIVLIK1sS\r\n" + 
			"8qOJghER2s+FrdW0mwGG3iXZ8CS6elQM9uDZfRjFqA0PBCyg0tavYUIIK4zmQ7VY\r\n" + 
			"VJu0Ty/t6OvhTRp4GDvUG7x723HWSnVZKYH9bPUZo1nEfE6ruRoaC3NaRF2pEFzr\r\n" + 
			"YUYM9c9b0hfDq8Vvhbs/YgZ9mPxojARF0MCao5DFdiG9B/MK6zo8hjHwQhS0Xyib\r\n" + 
			"+82nJMgxxwp2Dnxp/ebsRCX4BWSlgQlof7b7tsZ2DSXZ2Mbg3l3ZxA7GY/wPAB45\r\n" + 
			"gqSvYWxKHVdhkjh11UGEMFfcCo+H+YGGZ3iP9QIDAQABAoIBAHYBRo9Fj+exZpNn\r\n" + 
			"CBHpLgiE0CjWq27y5TdoDyEx4pGqzroVp1vNSkAXxpLIEjeIgMjxp296Ms5+0xVg\r\n" + 
			"vyBRP4bqgu7xSHxvi8f9KB+Bwn+HymS3IsX0VIFuwL3WQGqN+dLOKfkUNNNnT5cE\r\n" + 
			"otpumKX98AyUrmW44POKRUhy8blSragr6soZ1DvxPviPGnVTK2x3uOQAvcXF0NeF\r\n" + 
			"nWJc01Igmlla6zcO7E2ayByZLPeVZpjxtbAM71BSMpPKQGgyGpQRsUEMnD5xkKBz\r\n" + 
			"gy9nGhHLw6x906YIR/03KmfPTOOH4GyxbP56ZZ61rIbAHVxPyY/lXZ4k7IKkhbSJ\r\n" + 
			"qQn4RKECgYEAyC0MaJChxsCCh0KH0F4aiuKCd5UWir+0lNmVopjp9CYIdF8wJA1i\r\n" + 
			"TN85ucXS5F5OMrnq3McBAYCASjVOJvCXSw6QULd1Opu5G4E6ils9ykZNGY6CMVac\r\n" + 
			"KL552+FqYooN/LWJ/N3/72OeLGFdoGCcacXpaZ0B805UtNYxVykTELkCgYEAxlwk\r\n" + 
			"O7SJ5IOxYA9b71uqXZZn6UhUyCJT92IzoT3VkEJa8Vc9Jo51Gkv60CtRFAyhz5Or\r\n" + 
			"jjS6vfDgYKqTeaREPqNexksxcxF22HyfHaDRo4nw39/ReHuys0ppbvZyUSiEMAQO\r\n" + 
			"MWEIj0rT989zpZmaf0jw2IaPCOn7KxKEYwpJgx0CgYBbf1vgn9uSgYQoV1zE/X6c\r\n" + 
			"ZRTFnDwFh8L/FsWy4LpvgsLyjl/u2r9H6/Dy3V2cTQ7pWNnluIDZMudxBBSI8Af3\r\n" + 
			"otyMSI8pDuz5iYQ8oaAIyUGMoPsZmkEezpHc05hRGlFyZXiBQOgvgBVYq9ij6gUU\r\n" + 
			"xAYA5Gdz0yYc0psTAA9a4QKBgFOYE0ECJbai9UZ5UpI9/D8ympoKfIhXRzGTNhT2\r\n" + 
			"vSDthev9iuzLzngFmy7Ngn7DkBGttvfYKvbPI5A5cqsp2gTXzqIcoDtRGdfENT5E\r\n" + 
			"PvT/tf5zEZEH+FhsvKAhxi2DuKmPm/edYAEbVNczmxYn9J89uxpVIsRr7wjI5ffj\r\n" + 
			"JvXVAoGACyHoerGOofgkHQ0PnYB1Z6LpZv7DwY+k9hbvIdgqCrbP+qawRYmsP0Zu\r\n" + 
			"dowuTLLFXDFC31BTDV42apQRKYlIEx74TH5XRKW8XE3gjIcTBbpZiXo+5Yc8IFIc\r\n" + 
			"Qgzt6I8TnVnqVqGV7DeTzOTm+Ux23FGy2vlQ+iT5P8KauF+2q5c=\r\n" + 
			"-----END RSA PRIVATE KEY-----";
	public static final String RSA_PUBLICA = "-----BEGIN PUBLIC KEY-----\r\n" + 
			"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmxrkHlq9KdCp00nqWTWT\r\n" + 
			"azkvEwl5YJ0BxdsTuiiXIVLIK1sS8qOJghER2s+FrdW0mwGG3iXZ8CS6elQM9uDZ\r\n" + 
			"fRjFqA0PBCyg0tavYUIIK4zmQ7VYVJu0Ty/t6OvhTRp4GDvUG7x723HWSnVZKYH9\r\n" + 
			"bPUZo1nEfE6ruRoaC3NaRF2pEFzrYUYM9c9b0hfDq8Vvhbs/YgZ9mPxojARF0MCa\r\n" + 
			"o5DFdiG9B/MK6zo8hjHwQhS0Xyib+82nJMgxxwp2Dnxp/ebsRCX4BWSlgQlof7b7\r\n" + 
			"tsZ2DSXZ2Mbg3l3ZxA7GY/wPAB45gqSvYWxKHVdhkjh11UGEMFfcCo+H+YGGZ3iP\r\n" + 
			"9QIDAQAB\r\n" + 
			"-----END PUBLIC KEY-----";
}

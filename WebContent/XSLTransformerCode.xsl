<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                        				
                        xmlns="http://www.w3.org/1999/xhtml"
						xmlns:atom="http://www.w3.org/2005/Atom"
                        version="1.0"> 

	<xsl:template match="/">
		<html>
			<body>		
				<h1><xsl:value-of select="rss/channel/title"/></h1>
				<ul>
					<xsl:for-each select="rss/channel/item">			
						<li>
							<xsl:element name="a">
								<xsl:attribute name="href">
									<xsl:value-of select="atom:link/@href"/>						
								</xsl:attribute>
								<xsl:value-of select="title"/>
							</xsl:element>
						</li>	
					</xsl:for-each>						
				</ul>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet> 
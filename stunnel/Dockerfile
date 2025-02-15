FROM easypi/stunnel

# Copy the entrypoint script
COPY entrypoint.sh /entrypoint.sh

# Copy Stunnel configuration
COPY stunnel.conf /etc/stunnel/stunnel.conf

#RUN chmod 600 ./certs/server.key

# Copy certificates
COPY ./certs /etc/stunnel/certs

#RUN chmod 600 /etc/stunnel/certs/server.key
# Set the custom entrypoint \
#exec stunnel /etc/stunnel/stunnel.conf \

ENTRYPOINT ["stunnel", "/etc/stunnel/stunnel.conf"]
#ENTRYPOINT ["/entrypoint.sh"]
